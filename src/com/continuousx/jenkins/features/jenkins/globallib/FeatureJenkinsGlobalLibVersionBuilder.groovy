package com.continuousx.jenkins.features.jenkins.globallib

import com.cloudbees.groovy.cps.NonCPS
import com.continuousx.jenkins.logger.LogLevelType
import com.continuousx.jenkins.logger.PipelineLogger

class FeatureJenkinsGlobalLibVersionBuilder {

    private def jenkinsContext
    private FeatureJenkinsGlobalLibVersionConfig featureConfig = new FeatureJenkinsGlobalLibVersionConfig()
    private PipelineLogger logger

    @SuppressWarnings('GroovyUntypedAccess')
    FeatureJenkinsGlobalLibVersionBuilder(final def jenkinsContext) {
        Objects.requireNonNull(jenkinsContext)
        this.jenkinsContext = jenkinsContext
        this.logger = new PipelineLogger(this.jenkinsContext)
        this.logger.setLogLevelType(LogLevelType.WARNING)
    }

    FeatureJenkinsGlobalLibVersionBuilder withFeatureConfig(FeatureJenkinsGlobalLibVersionConfig featureConfig) {
        this.featureConfig = featureConfig
        this
    }

    FeatureJenkinsGlobalLibVersionBuilder withLogger(final PipelineLogger logger) {
        Objects.requireNonNull(logger)
        this.logger = logger
        this
    }

    @SuppressWarnings('GroovyUntypedAccess')
    FeatureJenkinsGlobalLibVersionImpl build() {
        new FeatureJenkinsGlobalLibVersionImpl(jenkinsContext, featureConfig)
    }

}
