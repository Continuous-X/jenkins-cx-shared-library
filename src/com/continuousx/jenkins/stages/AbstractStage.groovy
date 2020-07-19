package com.continuousx.jenkins.stages


import com.continuousx.jenkins.features.metrics.influxdb.InfluxDBFeature
import com.continuousx.jenkins.features.metrics.influxdb.InfluxDBFeatureBuilder
import com.continuousx.jenkins.features.metrics.influxdb.measurements.operating.MeasurementOperatingPipelineStage
import com.continuousx.jenkins.logger.PipelineLogger
import com.continuousx.utils.github.GitURLParser
import com.continuousx.utils.jenkins.JenkinsPluginCheck

abstract class AbstractStage implements Stage, Serializable {

    @SuppressWarnings(['SerialVersionUID', 'unused'])
    private static final long serialVersionUID = 1234567L

    def jenkinsContext
    /**
     * for code completion
     * org.jenkinsci.plugins.workflow.support.steps.build.RunWrapper
     */
    def currentBuild
    List<String> neededPlugins
    StageConfig stageConfig

    protected MeasurementOperatingPipelineStage measurement = new MeasurementOperatingPipelineStage()
    protected InfluxDBFeature metrics
    protected PipelineLogger logger

    @SuppressWarnings('GroovyUntypedAccess')
    protected AbstractStage(final def paramJenkinsContext, final List<String> paramNeededPlugins, final StageConfig paramStageConfig) {
        Objects.requireNonNull(paramJenkinsContext)
        Objects.requireNonNull(paramNeededPlugins)
        Objects.requireNonNull(paramStageConfig)

        neededPlugins = []
        jenkinsContext = paramJenkinsContext
        neededPlugins = paramNeededPlugins
        stageConfig = paramStageConfig
        currentBuild = this.jenkinsContext.currentBuild

        logger = new PipelineLogger(jenkinsContext: jenkinsContext, logLevelType: stageConfig.logLevelType)

        logger.logDebug("create stage ${stageConfig.type}")

        measurement.active = stageConfig.active
        measurement.failOnError = stageConfig.failOnError
        measurement.stageType = stageConfig.type
        if (this.jenkinsContext.env.GIT_URL != null) {
            final GitURLParser gitUrlParser = new GitURLParser(this.jenkinsContext.env.GIT_URL)
            measurement.setGHOrganization(gitUrlParser.getOrgaName())
            measurement.setGHRepository(gitUrlParser.getRepoName())
        }

        metrics = new InfluxDBFeatureBuilder(paramJenkinsContext).build()
        logger.logInfo("Stage Constructor ready")
        logger.logInfo("currentBuild ${currentBuild}")
        logger.logInfo("displayName ${currentBuild.displayName}")
        logger.logInfo("projectname ${currentBuild.projectName}")
        logger.logInfo("properties ${currentBuild.properties}")
        logger.logInfo("build variables ${currentBuild.buildVariables}")
    }

    @SuppressWarnings('GroovyUntypedAccess')
    boolean checkNeededPlugins() {
        return new JenkinsPluginCheck(jenkinsContext)
                .addInstalledPlugins()
                .addNeededPluginList(neededPlugins)
                .isPluginListInstalled()
    }

    abstract void runStageImpl()

    @SuppressWarnings('GroovyUntypedAccess')
    void runStage() {
        if (checkNeededPlugins()) {
            try {
                logger.logDebug("run stage ${stageConfig.type}")
                runStageImpl()
            } catch (final Exception exception) {
                logger.logDebug("stage ${stageConfig.type} throw Exception:\n${exception.getMessage()}")
                if (stageConfig.failOnError) {
                    logger.logDebug("stage ${stageConfig.type} throw Exception and failOnError is ${stageConfig.failOnError}\n\t throw ${exception.getClass().getName()}")
                    throw exception
                } else {
                    logger.logDebug("stage ${stageConfig.type} throw Exception and failOnError is ${stageConfig.failOnError}")
                    logger.logWarning("stage ${stageConfig.type} failed: ${exception.message}")
                }
            } finally {
                measurement.duration = currentBuild.timeInMillis
                publishMetricOperating()
            }
        } else {
            logger.logError("check needed plugins: ${neededPlugins}")
        }
    }

    @Override
    void publishMetricOperating() {
        logger.logDebug("stage ${stageConfig.type} publishMetricOperating with ${measurement.toString()}")
        metrics.publishMetricOperating(measurement)
    }

}
