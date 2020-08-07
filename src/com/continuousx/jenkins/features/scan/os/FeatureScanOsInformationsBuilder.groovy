package com.continuousx.jenkins.features.scan.os

class FeatureScanOsInformationsBuilder {

    private def jenkinsContext
    private FeatureScanOsInformationsConfig featureConfig = new FeatureScanOsInformationsConfig()

    @SuppressWarnings('GroovyUntypedAccess')
    FeatureScanOsInformationsBuilder(final def jenkinsContext) {
        Objects.requireNonNull(jenkinsContext)
        this.jenkinsContext = jenkinsContext
    }

    FeatureScanOsInformationsBuilder withFeatureConfig(FeatureScanOsInformationsConfig featureConfig) {
        this.featureConfig = featureConfig
        this
    }

    @SuppressWarnings('GroovyUntypedAccess')
    FeatureScanOsInformationsImpl build() {
        new FeatureScanOsInformationsImpl(jenkinsContext, featureConfig)
    }

}
