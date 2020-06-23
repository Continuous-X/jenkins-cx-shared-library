package com.continuousx.jenkins.pipelines

import com.cloudbees.groovy.cps.NonCPS
import com.continuousx.jenkins.LogLevelType
import com.continuousx.jenkins.features.github.GitURLParser
import com.continuousx.jenkins.features.metrics.influxdb.InfluxDBFeature
import com.continuousx.jenkins.features.metrics.influxdb.InfluxDBFeatureBuilder
import com.continuousx.jenkins.features.metrics.influxdb.measurements.operating.MeasurementOperatingPipeline
import com.continuousx.jenkins.pipelines.mavenbuild.PipelineMavenBuildConfig

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
    LogLevelType logLevel
    MeasurementOperatingPipeline measurement = new MeasurementOperatingPipeline()

    InfluxDBFeature metrics

    @SuppressWarnings(['NoDef', 'MethodParameterTypeRequired', 'GroovyUntypedAccess', 'GroovyAssignabilityCheck'])
    protected AbstractPipeline(
            def jenkinsContext,
            List<String> neededPlugins,
            PipelineConfig config) {
        Objects.requireNonNull(jenkinsContext)
        Objects.requireNonNull(neededPlugins)
        Objects.requireNonNull(config)
        Objects.requireNonNull(config.getLogLevelType())
        this.jenkinsContext = jenkinsContext
        this.neededPlugins = neededPlugins
        this.pipelineConfig = config
        this.logLevel = config.getLogLevelType()
        this.currentBuild = this.jenkinsContext.currentBuild

        this.jenkinsContext.echo("ENV: ${this.jenkinsContext.env.GIT_URL}")
        if (this.jenkinsContext.env.GIT_URL != null) {
            GitURLParser gitUrlParser = new GitURLParser(this.jenkinsContext.env.GIT_URL)
            measurement.setGHOrganization(gitUrlParser.getOrgaName())
            measurement.setGHRepository(gitUrlParser.getRepoName())
        }

        measurement.pipelineType = pipelineConfig.type

        metrics = new InfluxDBFeatureBuilder(jenkinsContext).build()
    }

    abstract void runPipelineImpl()

    void runPipeline() {
        try {
            runPipelineImpl()
        }catch (final Exception exception) {
            jenkinsContext.log.warning("${getPipelineConfig().type} failed: ${exception.message}")
            throw exception
        } finally {
            measurement.duration = currentBuild.timeInMillis
            publishMetricOperating()
        }
    }

    @Override
    @NonCPS
    PipelineConfig getConfig() {
        pipelineConfig
    }


    @Override
    void publishMetricOperating() {
        measurement.duration = currentBuild.timeInMillis
        metrics.publishMetricOperating(measurement)
    }

}
