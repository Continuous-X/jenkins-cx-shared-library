package com.continuousx.jenkins.features.maven.build.wrapper

import com.continuousx.jenkins.LogLevelType
import com.continuousx.jenkins.features.maven.dependencies.FeatureMavenDepToJenkinsPluginsTxtImpl
import com.continuousx.jenkins.features.metrics.influxdb.InfluxDBFeatureImpl

class FeatureMavenWrapperBuildBuilder {

    def jenkinsContext
    boolean failOnError = true
    LogLevelType logLevel = LogLevelType.INFO

    @SuppressWarnings('GroovyUntypedAccess')
    FeatureMavenWrapperBuildBuilder(final def jenkinsContext) {
        Objects.requireNonNull(jenkinsContext)
        this.jenkinsContext = jenkinsContext
    }

    void withFailOnError(boolean failOnError) {
        this.failOnError = failOnError
    }

    void withLogLevel(LogLevelType logLevel) {
        Objects.requireNonNull(logLevel)
        this.logLevel = logLevel
    }

    @SuppressWarnings('GroovyUntypedAccess')
    FeatureMavenDepToJenkinsPluginsTxtImpl build() {
        new FeatureMavenDepToJenkinsPluginsTxtImpl(jenkinsContext, failOnError, logLevel)
    }
}
