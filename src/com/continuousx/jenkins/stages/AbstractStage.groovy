package com.continuousx.jenkins.stages

import com.continuousx.jenkins.LogLevelType
import com.continuousx.jenkins.features.metrics.influxdb.InfluxDBFeature
import com.continuousx.jenkins.features.metrics.influxdb.InfluxDBFeatureBuilder
import com.continuousx.jenkins.features.metrics.influxdb.measurements.operating.MeasurementOperatingPipelineStage
import com.continuousx.utils.github.GitURLParser
import com.continuousx.utils.jenkins.JenkinsPluginCheck
import com.continuousx.utils.logger.Logger

abstract class AbstractStage implements Stage, Serializable {

    @SuppressWarnings(['SerialVersionUID', 'unused'])
    private static final long serialVersionUID = 1234567L

    def jenkinsContext
    /**
     * for code completion
     * org.jenkinsci.plugins.workflow.support.steps.build.RunWrapper
     */
    def currentBuild

    private MeasurementOperatingPipelineStage measurement = new MeasurementOperatingPipelineStage()
    private InfluxDBFeature metrics

    @SuppressWarnings('GroovyUntypedAccess')
    protected AbstractStage(
            final def paramJenkinsContext,
            final List<String> paramNeededPlugins,
            final StageConfig paramStageConfig) {
        Objects.requireNonNull(paramJenkinsContext)
        Objects.requireNonNull(paramNeededPlugins)
        Objects.requireNonNull(paramStageConfig)

        neededPlugins = []
        jenkinsContext = paramJenkinsContext
        neededPlugins << paramNeededPlugins
        stageConfig = paramStageConfig
        currentBuild = this.jenkinsContext.currentBuild

        logger = new Logger(jenkinsContext: jenkinsContext, logLevelType: stageConfig.logLevelType)

        logger.logDebug("create stage ${stageConfig.type}")
        this.jenkinsContext.log.info("ENV in Stage: ${this.jenkinsContext.env}")

        measurement.active = stageConfig.active
        measurement.failOnError = stageConfig.failOnError
        measurement.stageType = stageConfig.type
        if (this.jenkinsContext.env.GIT_URL != null) {
            final GitURLParser gitUrlParser = new GitURLParser(this.jenkinsContext.env.GIT_URL)
            measurement.setGHOrganization(gitUrlParser.getOrgaName())
            measurement.setGHRepository(gitUrlParser.getRepoName())
        }

        metrics = new InfluxDBFeatureBuilder(paramJenkinsContext).build()
        this.jenkinsContext.log.info("Stage Constructor ready")
        this.jenkinsContext.log.info("currentBuild ${currentBuild}")
        this.jenkinsContext.log.info("displayName ${currentBuild.displayName}")
        this.jenkinsContext.log.info("projectname ${currentBuild.projectName}")
        this.jenkinsContext.log.info("properties ${currentBuild.properties}")
        this.jenkinsContext.log.info("build variables ${currentBuild.buildVariables}")
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
        try {
            logger.logDebug("run stage ${stageConfig.type}")
            runStageImpl()
        }catch (final Exception exception) {
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
    }

    void publishMetricOperating() {
        logger.logDebug("stage ${stageConfig.type} publishMetricOperating with ${measurement.toString()}")
        metrics.publishMetricOperating(measurement)
    }

}
