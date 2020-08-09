package com.continuousx.jenkins.features.scan.network


import com.continuousx.jenkins.logger.LogLevelType
import com.continuousx.jenkins.logger.PipelineLogger

class FeatureScanNetworkInformationsBuilder {

    private def jenkinsContext
    private FeatureScanNetworkInformationsConfig featureConfig = new FeatureScanNetworkInformationsConfig()
    private PipelineLogger logger

    @SuppressWarnings('GroovyUntypedAccess')
    FeatureScanNetworkInformationsBuilder(final def jenkinsContext) {
        Objects.requireNonNull(jenkinsContext)
        this.jenkinsContext = jenkinsContext
        this.logger = new PipelineLogger(this.jenkinsContext)
        this.logger.setLogLevelType(LogLevelType.WARNING)
    }

    FeatureScanNetworkInformationsBuilder withFeatureConfig(FeatureScanNetworkInformationsConfig featureConfig) {
        this.featureConfig = featureConfig
        this
    }

    FeatureScanNetworkInformationsBuilder withLogger(final PipelineLogger logger) {
        Objects.requireNonNull(logger)
        this.logger = logger
        this
    }

    @SuppressWarnings('GroovyUntypedAccess')
    FeatureScanNetworkInformationsImpl build() {
        new FeatureScanNetworkInformationsImpl(jenkinsContext, featureConfig, logger)
    }

}
