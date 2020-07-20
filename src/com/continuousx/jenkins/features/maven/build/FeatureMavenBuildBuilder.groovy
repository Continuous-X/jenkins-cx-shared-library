package com.continuousx.jenkins.features.maven.build

class FeatureMavenBuildBuilder {

    private def jenkinsContext
    private FeatureMavenBuildConfig featureConfig = new FeatureMavenBuildConfig()

    @SuppressWarnings('GroovyUntypedAccess')
    FeatureMavenBuildBuilder(final def jenkinsContext) {
        Objects.requireNonNull(jenkinsContext)
        this.jenkinsContext = jenkinsContext
    }

    FeatureMavenBuildConfig withFeatureConfig(final FeatureMavenBuildConfig featureConfig) {
        Objects.requireNonNull(featureConfig)
        this.featureConfig = featureConfig
        return this
    }


    @SuppressWarnings('GroovyUntypedAccess')
    FeatureMavenBuildImpl build() {
        new FeatureMavenBuildImpl(jenkinsContext, featureConfig)
    }

}
