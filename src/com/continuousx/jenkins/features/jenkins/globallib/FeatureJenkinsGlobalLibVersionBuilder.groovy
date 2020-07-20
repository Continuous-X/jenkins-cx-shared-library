package com.continuousx.jenkins.features.jenkins.globallib

class FeatureJenkinsGlobalLibVersionBuilder {

    private def jenkinsContext
    private FeatureJenkinsGlobalLibVersionConfig featureConfig = new FeatureJenkinsGlobalLibVersionConfig()

    @SuppressWarnings('GroovyUntypedAccess')
    FeatureJenkinsGlobalLibVersionBuilder(final def jenkinsContext) {
        Objects.requireNonNull(jenkinsContext)
        this.jenkinsContext = jenkinsContext
    }

    FeatureJenkinsGlobalLibVersionBuilder withFeatureConfig(FeatureJenkinsGlobalLibVersionConfig featureConfig) {
        this.featureConfig = featureConfig
        this
    }

    @SuppressWarnings('GroovyUntypedAccess')
    FeatureJenkinsGlobalLibVersionImpl build() {
        new FeatureJenkinsGlobalLibVersionImpl(jenkinsContext, featureConfig)
    }

}
