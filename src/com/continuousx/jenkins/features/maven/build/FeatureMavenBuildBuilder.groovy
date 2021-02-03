package com.continuousx.jenkins.features.maven.build

import com.continuousx.jenkins.logger.LogLevelType
import com.continuousx.jenkins.logger.PipelineLogger

class FeatureMavenBuildBuilder {

    private def jenkinsContext
    private FeatureMavenBuildConfig featureConfig = new FeatureMavenBuildConfig()
    private PipelineLogger logger

    @SuppressWarnings('GroovyUntypedAccess')
    FeatureMavenBuildBuilder(final def jenkinsContext) {
        Objects.requireNonNull(jenkinsContext)
        this.jenkinsContext = jenkinsContext
        this.logger = new PipelineLogger(this.jenkinsContext)
        this.logger.setLogLevelType(LogLevelType.WARNING)
    }

    FeatureMavenBuildConfig withFeatureConfig(final FeatureMavenBuildConfig featureConfig) {
        Objects.requireNonNull(featureConfig)
        this.featureConfig = featureConfig
        return this
    }

    FeatureMavenBuildConfig withLogger(final PipelineLogger logger) {
        Objects.requireNonNull(logger)
        this.logger = logger
        this
    }

    @SuppressWarnings('GroovyUntypedAccess')
    FeatureMavenBuildImpl build() {
        new FeatureMavenBuildImpl(jenkinsContext, featureConfig, logger)
    }

}
