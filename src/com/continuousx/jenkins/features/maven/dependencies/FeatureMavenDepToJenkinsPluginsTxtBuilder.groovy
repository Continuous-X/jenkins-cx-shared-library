package com.continuousx.jenkins.features.maven.dependencies

import com.continuousx.jenkins.features.maven.build.FeatureMavenBuildConfig
import com.continuousx.jenkins.logger.LogLevelType
import com.continuousx.jenkins.logger.PipelineLogger

class FeatureMavenDepToJenkinsPluginsTxtBuilder {

    private def jenkinsContext
    private FeatureMavenDepToJenkinsPluginsTxtConfig featureConfig = new FeatureMavenDepToJenkinsPluginsTxtConfig()
    private PipelineLogger logger

    @SuppressWarnings('GroovyUntypedAccess')
    FeatureMavenDepToJenkinsPluginsTxtBuilder(final def jenkinsContext) {
        Objects.requireNonNull(jenkinsContext)
        this.jenkinsContext = jenkinsContext
        this.logger = new PipelineLogger(this.jenkinsContext)
        this.logger.setLogLevelType(LogLevelType.WARNING)
    }

    FeatureMavenDepToJenkinsPluginsTxtBuilder withFeatureConfig(FeatureMavenDepToJenkinsPluginsTxtConfig featureConfig) {
        this.featureConfig = featureConfig
        this
    }

    FeatureMavenDepToJenkinsPluginsTxtBuilder withLogger(final PipelineLogger logger) {
        Objects.requireNonNull(logger)
        this.logger = logger
        this
    }

    @SuppressWarnings('GroovyUntypedAccess')
    FeatureMavenDepToJenkinsPluginsTxtImpl build() {
        new FeatureMavenDepToJenkinsPluginsTxtImpl(jenkinsContext, featureConfig, logger)
    }

}
