package com.continuousx.jenkins.stages

import com.cloudbees.groovy.cps.NonCPS
import com.continuousx.utils.github.GitURLParser
import com.continuousx.utils.jenkins.JenkinsPluginCheck
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
            final StageConfig stageConfig) {
        Objects.requireNonNull(jenkinsContext)
        Objects.requireNonNull(neededPlugins)
        Objects.requireNonNull(stageConfig)
        this.jenkinsContext = jenkinsContext
        this.neededPlugins = neededPlugins
        this.stageConfig = stageConfig
        this.currentBuild = this.jenkinsContext.currentBuild

        this.jenkinsContext.log.info("ENV in Stage: ${this.jenkinsContext.env}")

        measurement.active = stageConfig.active
        measurement.failOnError = stageConfig.failOnError
        measurement.stageType = stageConfig.type
        if (this.jenkinsContext.env.GIT_URL != null) {
            final GitURLParser gitUrlParser = new GitURLParser(this.jenkinsContext.env.GIT_URL)
            measurement.setGHOrganization(gitUrlParser.getOrgaName())
            measurement.setGHRepository(gitUrlParser.getRepoName())
        }

        metrics = new InfluxDBFeatureBuilder(jenkinsContext).build()
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

    @NonCPS
    StageConfig getStageConfig() {
        stageConfig
    }

    void publishMetricOperating() {
        metrics.publishMetricOperating(measurement)
    }

}
