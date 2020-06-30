package com.continuousx.jenkins.features.maven.dependencies

class FeatureMavenDepToJenkinsPluginsTxtBuilder {

    private def jenkinsContext
    private FeatureMavenDepToJenkinsPluginsTxtConfig featureConfig = new FeatureMavenDepToJenkinsPluginsTxtConfig()

    @SuppressWarnings('GroovyUntypedAccess')
    FeatureMavenDepToJenkinsPluginsTxtBuilder(final def jenkinsContext) {
        Objects.requireNonNull(jenkinsContext)
        this.jenkinsContext = jenkinsContext
    }

    FeatureMavenDepToJenkinsPluginsTxtBuilder withFeatureConfig(FeatureMavenDepToJenkinsPluginsTxtConfig featureConfig) {
        this.featureConfig = featureConfig
        this
    }

    @SuppressWarnings('GroovyUntypedAccess')
    FeatureMavenDepToJenkinsPluginsTxtImpl build() {
        new FeatureMavenDepToJenkinsPluginsTxtImpl(jenkinsContext, featureConfig)
    }

}
