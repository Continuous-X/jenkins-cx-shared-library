package com.continuousx.jenkins.features.maven.build

import com.continuousx.jenkins.LogLevelType

class FeatureMavenBuildBuilder {

    def jenkinsContext
    boolean failOnError = true
    LogLevelType logLevel = LogLevelType.INFO

    @SuppressWarnings('GroovyUntypedAccess')
    FeatureMavenBuildBuilder(final def jenkinsContext) {
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
    FeatureMavenBuildImpl build() {
        new FeatureMavenBuildImpl(jenkinsContext, failOnError, logLevel)
    }
}
