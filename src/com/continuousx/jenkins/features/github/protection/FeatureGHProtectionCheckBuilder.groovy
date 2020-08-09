package com.continuousx.jenkins.features.github.protection

import com.cloudbees.groovy.cps.NonCPS
import com.continuousx.jenkins.logger.LogLevelType
import com.continuousx.jenkins.logger.PipelineLogger

class FeatureGHProtectionCheckBuilder {

    private def jenkinsContext
    private FeatureGHProtectionCheckConfig featureConfig = new FeatureGHProtectionCheckConfig()
    private PipelineLogger logger

    @SuppressWarnings('GroovyUntypedAccess')
    FeatureGHProtectionCheckBuilder(final def jenkinsContext) {
        Objects.requireNonNull(jenkinsContext)
        this.jenkinsContext = jenkinsContext
        this.logger = new PipelineLogger(this.jenkinsContext)
        this.logger.setLogLevelType(LogLevelType.WARNING)
    }

    FeatureGHProtectionCheckBuilder withFeatureConfig(FeatureGHProtectionCheckConfig featureConfig) {
        this.featureConfig = featureConfig
        this
    }

    FeatureGHProtectionCheckBuilder withLogger(final PipelineLogger logger) {
        Objects.requireNonNull(logger)
        this.logger = logger
        this
    }

    @SuppressWarnings('GroovyUntypedAccess')
    FeatureGHProtectionCheckImpl build() {
        new FeatureGHProtectionCheckImpl(jenkinsContext, featureConfig, logger)
    }

}
