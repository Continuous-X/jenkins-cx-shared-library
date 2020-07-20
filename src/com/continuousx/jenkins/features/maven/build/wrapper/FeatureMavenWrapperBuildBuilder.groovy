package com.continuousx.jenkins.features.maven.build.wrapper

class FeatureMavenWrapperBuildBuilder {

    def jenkinsContext
    FeatureMavenWrapperBuildConfig featureConfig = new FeatureMavenWrapperBuildConfig()

    @SuppressWarnings('GroovyUntypedAccess')
    FeatureMavenWrapperBuildBuilder(final def jenkinsContext) {
        Objects.requireNonNull(jenkinsContext)
        this.jenkinsContext = jenkinsContext
    }

    FeatureMavenWrapperBuildBuilder withFeatureConfig(final FeatureMavenWrapperBuildConfig featureConfig) {
        Objects.requireNonNull(featureConfig)
        this.featureConfig = featureConfig
        this
    }

    @SuppressWarnings('GroovyUntypedAccess')
    FeatureMavenWrapperBuildImpl build() {
        new FeatureMavenWrapperBuildImpl(jenkinsContext, featureConfig)
    }
}
