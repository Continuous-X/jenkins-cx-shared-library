package com.continuousx.jenkins.features.maven.dependencies

import com.continuousx.jenkins.LogLevelType

class FeatureMavenDepToJenkinsPluginsTxtBuilder {

    def jenkinsContext
    boolean failOnError = true
    LogLevelType logLevel = LogLevelType.INFO

    @SuppressWarnings('GroovyUntypedAccess')
    FeatureMavenDepToJenkinsPluginsTxtBuilder(final def jenkinsContext) {
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
