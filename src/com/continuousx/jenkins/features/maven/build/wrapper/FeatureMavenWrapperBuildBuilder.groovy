package com.continuousx.jenkins.features.maven.build.wrapper


import com.continuousx.jenkins.features.maven.build.FeatureMavenBuildConfig

class FeatureMavenWrapperBuildBuilder {

    def jenkinsContext
    FeatureMavenBuildConfig featureConfig = new FeatureMavenBuildConfig()

    @SuppressWarnings('GroovyUntypedAccess')
    FeatureMavenWrapperBuildBuilder(final def jenkinsContext) {
        Objects.requireNonNull(jenkinsContext)
        this.jenkinsContext = jenkinsContext
    }

    FeatureMavenWrapperBuildConfig withFeatureConfig(final FeatureMavenWrapperBuildConfig featureConfig) {
        this.featureConfig = featureConfig
        this
    }

    @SuppressWarnings('GroovyUntypedAccess')
    FeatureMavenWrapperBuildImpl build() {
        new FeatureMavenWrapperBuildImpl(jenkinsContext, featureConfig)
    }
}
