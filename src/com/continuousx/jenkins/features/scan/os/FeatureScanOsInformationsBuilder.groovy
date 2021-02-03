package com.continuousx.jenkins.features.scan.os

import com.continuousx.jenkins.logger.LogLevelType
import com.continuousx.jenkins.logger.PipelineLogger

class FeatureScanOsInformationsBuilder {

    private def jenkinsContext
    private FeatureScanOsInformationsConfig featureConfig = new FeatureScanOsInformationsConfig()
    private PipelineLogger logger

    @SuppressWarnings('GroovyUntypedAccess')
    FeatureScanOsInformationsBuilder(final def jenkinsContext) {
        Objects.requireNonNull(jenkinsContext)
        this.jenkinsContext = jenkinsContext
        this.logger = new PipelineLogger(this.jenkinsContext)
        this.logger.setLogLevelType(LogLevelType.WARNING)
    }

    FeatureScanOsInformationsBuilder withFeatureConfig(FeatureScanOsInformationsConfig featureConfig) {
        this.featureConfig = featureConfig
        this
    }

    FeatureScanOsInformationsBuilder withLogger(final PipelineLogger logger) {
        Objects.requireNonNull(logger)
        this.logger = logger
        this
    }

    @SuppressWarnings('GroovyUntypedAccess')
    FeatureScanOsInformationsImpl build() {
        new FeatureScanOsInformationsImpl(jenkinsContext, featureConfig, logger)
    }

}
