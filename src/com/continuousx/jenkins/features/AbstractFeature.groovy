package com.continuousx.jenkins.features

import com.cloudbees.groovy.cps.NonCPS
import com.continuousx.jenkins.LogLevelType
import com.continuousx.utils.github.GHBase
import com.continuousx.utils.github.GitURLParser
import com.continuousx.utils.jenkins.JenkinsConfig
import com.continuousx.utils.jenkins.JenkinsPluginCheck
import com.continuousx.jenkins.features.metrics.influxdb.InfluxDBFeature
import com.continuousx.jenkins.features.metrics.influxdb.InfluxDBFeatureBuilder
import com.continuousx.jenkins.features.metrics.influxdb.measurements.operating.MeasurementOperatingFeature
import org.kohsuke.github.GHCommitState
import org.kohsuke.github.GHRepository

abstract class AbstractFeature implements Feature, Serializable{

    @SuppressWarnings(['SerialVersionUID', 'unused'])
    private static final long serialVersionUID = 1234567L

    def jenkinsContext
    List<String> neededPlugins = []
    MeasurementOperatingFeature measurementOperating = new MeasurementOperatingFeature()
    FeatureConfig config
    InfluxDBFeature metrics
    GHBase ghBase

    @SuppressWarnings('GroovyUntypedAccess')
    protected AbstractFeature(
            final def jenkinsContext,
            final List<String> neededPlugins,
            final FeatureConfig featureConfig) {
        Objects.requireNonNull(jenkinsContext)
        Objects.requireNonNull(neededPlugins)
        Objects.requireNonNull(featureConfig)
        this.jenkinsContext = jenkinsContext
        this.neededPlugins = neededPlugins
        this.config = featureConfig

        this.jenkinsContext.log.info("ENV in Feature: ${this.jenkinsContext.env.toString()}")
        measurementOperating.featureType = this.config.type
        if (this.jenkinsContext.env.GIT_URL != null) {
            final GitURLParser gitUrlParser = new GitURLParser(this.jenkinsContext.env.GIT_URL)
            measurementOperating.setGHOrganization(gitUrlParser.getOrgaName())
            measurementOperating.setGHRepository(gitUrlParser.getRepoName())
        }
        this.jenkinsContext.withCredentials([this.jenkinsContext.usernamePassword(credentialsId: JenkinsConfig.JENKINS_CONFIG_CREDENTIAL_ID_GITHUB_API, usernameVariable: 'USERNAME', passwordVariable: 'TOKEN')]) {
            ghBase = new GHBase(this.jenkinsContext.env.GIT_URL, this.jenkinsContext.TOKEN)
        }
        metrics = new InfluxDBFeatureBuilder(jenkinsContext).build()
    }

    @SuppressWarnings('GroovyUntypedAccess')
    @NonCPS
    boolean checkNeededPlugins() {
        return new JenkinsPluginCheck(jenkinsContext)
                .addInstalledPlugins()
                .addNeededPluginList(neededPlugins)
                .isPluginListInstalled()
    }

    abstract void runFeatureImpl()

    @Override
    void runFeature() {
        if(checkNeededPlugins()) {
            try {
                final long startTime = System.nanoTime()
                ghBase.getRepository().createCommitStatus(this.jenkinsContext.GIT_COMMIT, GHCommitState.PENDING, this.jenkinsContext.GIT_URL, 'my description - pending' )
                runFeatureImpl()
                ghBase.getRepository().createCommitStatus(this.jenkinsContext.GIT_COMMIT, GHCommitState.SUCCESS, this.jenkinsContext.GIT_URL, 'my description - success' )
                final long duration = (long) ((System.nanoTime() - startTime) / 100000)
                measurementOperating.setDuration(duration)
            } catch (final Exception exception) {
                if (this.config.failOnError) {
                    ghBase.getRepository().createCommitStatus(this.jenkinsContext.GIT_COMMIT, GHCommitState.ERROR, this.jenkinsContext.GIT_URL, 'my description - error' )
                    throw exception
                } else {
                    ghBase.getRepository().createCommitStatus(this.jenkinsContext.GIT_COMMIT, GHCommitState.FAILURE, this.jenkinsContext.GIT_URL, 'my description - failure' )
                    jenkinsContext.log.warning("${this.config.type} failed: ${exception.message}")
                }
            } finally {
                publishMetricOperating()
            }
        } else {
            jenkinsContext.log.error("check needed plugins: ${neededPlugins}")
            publishMetricOperating()
        }
    }

    void publishMetricOperating() {
        metrics.publishMetricOperating(measurementOperating)
    }

}
