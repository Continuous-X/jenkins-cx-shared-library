package com.continuousx.jenkins.features

import com.cloudbees.groovy.cps.NonCPS
import com.continuousx.jenkins.features.metrics.influxdb.InfluxDBFeature
import com.continuousx.jenkins.features.metrics.influxdb.InfluxDBFeatureBuilder
import com.continuousx.jenkins.features.metrics.influxdb.measurements.operating.MeasurementOperatingFeature
import com.continuousx.utils.github.GHBase
import com.continuousx.utils.github.GitURLParser
import com.continuousx.utils.jenkins.JenkinsConfig
import com.continuousx.utils.jenkins.JenkinsPluginCheck
import org.kohsuke.github.GHCommitState

abstract class AbstractFeature implements Feature, Serializable{

    @SuppressWarnings(['SerialVersionUID', 'unused'])
    private static final long serialVersionUID = 1234567L

    protected def jenkinsContext
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

        measurementOperating.featureType = this.config.type
        if (this.jenkinsContext.env.GIT_URL != null) {
            final GitURLParser gitUrlParser = new GitURLParser(this.jenkinsContext.env.GIT_URL)
            measurementOperating.setGHOrganization(gitUrlParser.getOrgaName())
            measurementOperating.setGHRepository(gitUrlParser.getRepoName())
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
    @NonCPS
    void runFeature() {
        if(checkNeededPlugins()) {
            try {
                final long startTime = System.nanoTime()
                this.jenkinsContext.withCredentials([this.jenkinsContext.usernamePassword(credentialsId: JenkinsConfig.JENKINS_CONFIG_CREDENTIAL_ID_GITHUB_API, usernameVariable: 'USERNAME', passwordVariable: 'TOKEN')]) {
                    ghBase = new GHBase(this.jenkinsContext.env.GIT_URL, this.jenkinsContext.TOKEN)
                }
                ghBase.getRepository().createCommitStatus(this.jenkinsContext.env.GIT_COMMIT, GHCommitState.PENDING, this.jenkinsContext.env.GIT_URL, 'my description - pending' )
                runFeatureImpl()
                ghBase.getRepository().createCommitStatus(this.jenkinsContext.env.GIT_COMMIT, GHCommitState.SUCCESS, this.jenkinsContext.env.GIT_URL, 'my description - success' )
                final long duration = (long) ((System.nanoTime() - startTime) / 100000)
                measurementOperating.setDuration(duration)
            } catch (final Exception exception) {
                if (this.config.failOnError) {
                    ghBase.getRepository().createCommitStatus(this.jenkinsContext.env.GIT_COMMIT, GHCommitState.ERROR, this.jenkinsContext.env.GIT_URL, 'my description - error' )
                    throw exception
                } else {
/*
                    ghBase.getRepository().createCommitStatus(this.jenkinsContext.env.GIT_COMMIT, GHCommitState.FAILURE, this.jenkinsContext.env.GIT_URL, 'my description - failure' )
*/
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

    @Override
    void publishMetricOperating() {
        metrics.publishMetricOperating(measurementOperating)
    }

}
