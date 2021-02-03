package com.continuousx.jenkins.pipelines


import com.continuousx.jenkins.features.metrics.influxdb.InfluxDBFeature
import com.continuousx.jenkins.features.metrics.influxdb.InfluxDBFeatureBuilder
import com.continuousx.jenkins.features.metrics.influxdb.measurements.operating.MeasurementOperatingPipeline
import com.continuousx.jenkins.logger.LogLevelType
import com.continuousx.jenkins.logger.PipelineLogger
import com.continuousx.utils.github.GitURLParser

abstract class AbstractPipeline implements Pipeline, Serializable {

    private static final long serialVersionUID = 1234567L

    def jenkinsContext
    /**
     * fuer Code Completion kann man die Klasse
     * org.jenkinsci.plugins.workflow.support.steps.build.RunWrapper
     * nutzen.... beim Test macht es allerdings Probleme, da es eine Final Class ist
     * und man einen Mock nicht nutzen kann
     */
    def currentBuild
    List<String> neededPlugins = []
    PipelineConfig pipelineConfig
    MeasurementOperatingPipeline measurement = new MeasurementOperatingPipeline()
    InfluxDBFeature metrics
    protected PipelineLogger logger

    @SuppressWarnings(['NoDef', 'MethodParameterTypeRequired', 'GroovyUntypedAccess', 'GroovyAssignabilityCheck'])
    protected AbstractPipeline(
            def jenkinsContext,
            List<String> neededPlugins,
            PipelineConfig config) {
        Objects.requireNonNull(jenkinsContext)
        Objects.requireNonNull(neededPlugins)
        Objects.requireNonNull(config)

        this.jenkinsContext = jenkinsContext
        this.neededPlugins = neededPlugins
        this.pipelineConfig = config
        this.currentBuild = this.jenkinsContext.currentBuild

        this.logger = new PipelineLogger(this.jenkinsContext)
        this.logger.setLogLevelType(this.pipelineConfig.getLogLevelType())

        if (this.jenkinsContext.env.GIT_URL != null) {
            GitURLParser gitUrlParser = new GitURLParser(this.jenkinsContext.env.GIT_URL)
            measurement.setGHOrganization(gitUrlParser.getOrgaName())
            measurement.setGHRepository(gitUrlParser.getRepoName())
        }

        measurement.pipelineType = pipelineConfig.type
        metrics = new InfluxDBFeatureBuilder(jenkinsContext).withLogger(logger).build()
    }

    abstract void runPipelineImpl()

    void runPipeline() {
        try {
            logger.logDebug("start pipeline '${pipelineConfig.type}'")
            runPipelineImpl()
        }catch (final Exception exception) {
            logger.logWarning("${getPipelineConfig().type} failed: ${exception.message}")
            throw exception
        } finally {
            logger.logDebug("ended pipeline '${pipelineConfig.type}' - duration '${currentBuild.timeInMillis}'")
            publishMetricOperating()
        }
    }

    @Override
    void publishMetricOperating() {
        logger.logDebug("publish operating mertric for '${pipelineConfig.type}' - duration '${currentBuild.timeInMillis}'")
        measurement.duration = currentBuild.timeInMillis
        metrics.publishMetricOperating(measurement)
    }

}
