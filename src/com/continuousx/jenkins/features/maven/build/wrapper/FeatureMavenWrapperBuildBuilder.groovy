package com.continuousx.jenkins.features.maven.build.wrapper

import com.continuousx.jenkins.features.maven.build.FeatureMavenBuildConfig
import com.continuousx.jenkins.logger.LogLevelType
import com.continuousx.jenkins.logger.PipelineLogger

class FeatureMavenWrapperBuildBuilder {

    private def jenkinsContext
    private FeatureMavenWrapperBuildConfig featureConfig = new FeatureMavenWrapperBuildConfig()
    private PipelineLogger logger


    @SuppressWarnings('GroovyUntypedAccess')
    FeatureMavenWrapperBuildBuilder(final def jenkinsContext) {
        Objects.requireNonNull(jenkinsContext)
        this.jenkinsContext = jenkinsContext
        this.logger = new PipelineLogger(this.jenkinsContext)
        this.logger.setLogLevelType(LogLevelType.WARNING)
    }

    FeatureMavenWrapperBuildBuilder withFeatureConfig(final FeatureMavenWrapperBuildConfig featureConfig) {
        Objects.requireNonNull(featureConfig)
        this.featureConfig = featureConfig
        this
    }

    FeatureMavenWrapperBuildBuilder withLogger(final PipelineLogger logger) {
        Objects.requireNonNull(logger)
        this.logger = logger
        this
    }

    @SuppressWarnings('GroovyUntypedAccess')
    FeatureMavenWrapperBuildImpl build() {
        new FeatureMavenWrapperBuildImpl(jenkinsContext, featureConfig, logger)
    }
}
