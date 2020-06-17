package com.continuousx.jenkins.stages

import com.continuousx.jenkins.LogLevelType
import com.continuousx.jenkins.features.github.GitURLParser
import com.continuousx.jenkins.features.jenkins.utils.JenkinsPluginCheck
import com.continuousx.jenkins.features.metrics.influxdb.InfluxDBFeature
import com.continuousx.jenkins.features.metrics.influxdb.InfluxDBFeatureBuilder
import com.continuousx.jenkins.features.metrics.influxdb.measurements.operating.MeasurementOperatingPipelineStage

abstract class AbstractStage implements Stage, Serializable {

    @SuppressWarnings(['SerialVersionUID', 'unused'])
    private static final long serialVersionUID = 1234567L

    def jenkinsContext
    /**
     * for code completion
     * org.jenkinsci.plugins.workflow.support.steps.build.RunWrapper
     */
    def currentBuild
    List<String> neededPlugins = []
    StageConfig stageConfig

    MeasurementOperatingPipelineStage measurement = new MeasurementOperatingPipelineStage()
    InfluxDBFeature metrics

    @SuppressWarnings('GroovyUntypedAccess')
    protected AbstractStage(
            final def jenkinsContext,
            final List<String> neededPlugins,
            final StageConfig config) {
        Objects.requireNonNull(jenkinsContext)
        Objects.requireNonNull(neededPlugins)
        Objects.requireNonNull(config)
        this.jenkinsContext = jenkinsContext
        this.neededPlugins = neededPlugins
        this.stageConfig = config
        this.currentBuild = this.jenkinsContext.currentBuild

        measurement.active = config.isActive()
        measurement.failOnError = config.isFailOnError()
        measurement.stageType = config.getType()

        this.jenkinsContext.echo("ENV: ${this.jenkinsContext.env}")
        if (this.jenkinsContext.env.GIT_URL != null) {
            final GitURLParser gitUrlParser = new GitURLParser(this.jenkinsContext.env.GIT_URL)
            measurement.setGHOrganization(gitUrlParser.getOrgaName())
            measurement.setGHRepository(gitUrlParser.getRepoName())
        }

        metrics = new InfluxDBFeatureBuilder(jenkinsContext).build()
        this.jenkinsContext.echo("Stage Constructor ready")
        this.jenkinsContext.echo("currentBuild ${currentBuild}")
        this.jenkinsContext.echo("displayName ${currentBuild.displayName}")
        this.jenkinsContext.echo("projectname ${currentBuild.projectName}")
        this.jenkinsContext.echo("properties ${currentBuild.properties}")
        this.jenkinsContext.echo("build variables ${currentBuild.buildVariables}")
    }

    @SuppressWarnings('GroovyUntypedAccess')
    boolean checkNeededPlugins() {
        return new JenkinsPluginCheck(jenkinsContext)
                .addInstalledPlugins()
                .addNeededPluginList(neededPlugins)
                .isPluginListInstalled()
    }

    abstract void runStageImpl()

    void runStage() {
        try {
            runStageImpl()
        }catch (final Exception exception) {
            if (getStageConfig().failOnError) {
                throw exception
            } else {
                jenkinsContext.log.warning("${getStageConfig().type} failed: ${exception.message}")
            }
        } finally {
            measurement.duration = currentBuild.timeInMillis
            publishMetricOperating()
        }
    }

    StageConfig getConfig() {
        stageConfig
    }

    void publishMetricOperating() {
        metrics.publishMetricOperating(measurement)
    }

}
