package com.continuousx.jenkins.features

import com.continuousx.jenkins.features.metrics.influxdb.InfluxDBFeature
import com.continuousx.jenkins.features.metrics.influxdb.InfluxDBFeatureBuilder
import com.continuousx.jenkins.features.metrics.influxdb.measurements.operating.MeasurementOperatingFeature
import com.continuousx.jenkins.logger.PipelineLogger
import com.continuousx.utils.github.GHBase
import com.continuousx.utils.github.GitURLParser
import com.continuousx.utils.jenkins.JenkinsConfig
import com.continuousx.utils.jenkins.JenkinsPluginCheck
import org.kohsuke.github.GHCommitState

abstract class AbstractFeature implements Feature, Serializable{

    @SuppressWarnings(['SerialVersionUID', 'unused'])
    private static final long serialVersionUID = 1234567L

    def jenkinsContext
    List<String> neededPlugins = []
    FeatureConfig featureConfig
    MeasurementOperatingFeature measurementOperating = new MeasurementOperatingFeature()
    InfluxDBFeature metrics
    PipelineLogger logger
    GHBase ghBase

    @SuppressWarnings('GroovyUntypedAccess')
    protected AbstractFeature(
            final def paramJenkinsContext,
            final List<String> paramNeededPlugins,
            final FeatureConfig paramFeatureConfig,
            final PipelineLogger logger) {
        Objects.requireNonNull(paramJenkinsContext)
        Objects.requireNonNull(paramNeededPlugins)
        Objects.requireNonNull(paramFeatureConfig)
        Objects.requireNonNull(logger)

        neededPlugins = []
        jenkinsContext = paramJenkinsContext
        neededPlugins = paramNeededPlugins
        featureConfig = paramFeatureConfig

        this.logger = logger
        logger.logDebug"create feature ${featureConfig.type}"

        measurementOperating.featureType = featureConfig.type
        if (this.jenkinsContext.env.GIT_URL != null) {
            final GitURLParser gitUrlParser = new GitURLParser(this.jenkinsContext.env.GIT_URL)
            measurementOperating.setGHOrganization(gitUrlParser.getOrgaName())
            measurementOperating.setGHRepository(gitUrlParser.getRepoName())
        }

        metrics = new InfluxDBFeatureBuilder(jenkinsContext).withLogger(logger).build()
    }

    @SuppressWarnings('GroovyUntypedAccess')
    boolean checkNeededPlugins() {
        return new JenkinsPluginCheck(jenkinsContext)
                .withLogger(this.logger)
                .addInstalledPlugins()
                .addNeededPluginList(neededPlugins)
                .isPluginListInstalled()
    }

    abstract void runFeatureImpl()

    @SuppressWarnings('GroovyUntypedAccess')
    @Override
    void runFeature() {
        if(checkNeededPlugins()) {
            try {
                final long startTime = System.nanoTime()
                publishGHCommitStatus(GHCommitState.PENDING,'start feature')
                runFeatureImpl()
                publishGHCommitStatus(GHCommitState.SUCCESS,'feature success')
                final long duration = (long) ((System.nanoTime() - startTime) / 100000)
                measurementOperating.setDuration(duration)
            } catch (final Exception exception) {
                if (featureConfig.failOnError) {
                    publishGHCommitStatus(GHCommitState.ERROR,"feature failed with failOnError (${featureConfig.failOnError}) and with error: ${exception.getClass().getName()}")
                    logger.logError("${featureConfig.type} failed: ${exception.message}")
                    throw exception
                } else {
                    publishGHCommitStatus(GHCommitState.FAILURE,"feature failed with failOnError (${featureConfig.failOnError}) and with error: ${exception.message}")
                    logger.logWarning("${featureConfig.type} failed: ${exception.message}")
                }
            } finally {
                publishMetricOperating()
            }
        } else {
            publishGHCommitStatus(GHCommitState.ERROR,"jenkins plugins are missing: ${neededPlugins}")
            logger.logError("check needed plugins: ${neededPlugins}")
            publishMetricOperating()
        }
    }

    @Override
    void publishMetricOperating() {
        metrics.publishMetricOperating(measurementOperating)
    }

    @SuppressWarnings('GroovyUntypedAccess')
    void publishGHCommitStatus(final GHCommitState commitState, final String description) {
        Objects.requireNonNull(commitState)
        Objects.requireNonNull(description)
        assert description.length() <= 140: 'description is too long (maximum is 140 characters) / see https://developer.github.com/v3/repos/statuses/#create-a-status'
        this.jenkinsContext.withCredentials([this.jenkinsContext.usernamePassword(credentialsId: JenkinsConfig.JENKINS_CONFIG_CREDENTIAL_ID_GITHUB_API, usernameVariable: 'USERNAME', passwordVariable: 'TOKEN')]) {
            GHBase.getRepository(
                    this.jenkinsContext.env.GIT_URL as String,
                    GHBase.getConnetctionOAuth(this.jenkinsContext.TOKEN as String)
            ).createCommitStatus(
                    this.jenkinsContext.env.GIT_COMMIT as String,
                    commitState,
                    this.jenkinsContext.env.GIT_URL as String,
                    description,
                    "${GHBase.GH_COMMIT_STATE_CONTEXT_SHARED_LIB}/${featureConfig.type}}"
            )
        }
    }
}
