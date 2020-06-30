package com.continuousx.jenkins.features.github.protection

class FeatureGHProtectionCheckBuilder {

    private def jenkinsContext
    private FeatureGHProtectionCheckConfig featureConfig = new FeatureGHProtectionCheckConfig()

    @SuppressWarnings('GroovyUntypedAccess')
    FeatureGHProtectionCheckBuilder(final def jenkinsContext) {
        Objects.requireNonNull(jenkinsContext)
        this.jenkinsContext = jenkinsContext
    }

    FeatureGHProtectionCheckBuilder withFeatureConfig(FeatureGHProtectionCheckConfig featureConfig) {
        this.featureConfig = featureConfig
        this
    }

    @SuppressWarnings('GroovyUntypedAccess')
    FeatureGHProtectionCheckImpl build() {
        new FeatureGHProtectionCheckImpl(jenkinsContext, featureConfig)
    }
}
