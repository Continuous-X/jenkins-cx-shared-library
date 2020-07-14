package com.continuousx.utils.logger

import com.cloudbees.groovy.cps.NonCPS
import com.continuousx.jenkins.LogLevelType

class Logger {

    def jenkinsContext
    LogLevelType logLevelType

    @SuppressWarnings('GroovyUntypedAccess')
    @NonCPS
    void debug(final String logText) {
        Objects.requireNonNull(logText)
        Objects.requireNonNull(logLevelType)
        Objects.requireNonNull(jenkinsContext)
        logLevelType == LogLevelType.DEBUG ? this.jenkinsContext.log.debug(logText) : ''
    }

    @SuppressWarnings('GroovyUntypedAccess')
    @NonCPS
    void info(final String logText) {
        Objects.requireNonNull(logText)
        Objects.requireNonNull(logLevelType)
        Objects.requireNonNull(jenkinsContext)
        logLevelType == LogLevelType.INFO ? this.jenkinsContext.log.info(logText) : ''
    }

    @SuppressWarnings('GroovyUntypedAccess')
    @NonCPS
    void warning(final String logText) {
        Objects.requireNonNull(logText)
        Objects.requireNonNull(logLevelType)
        Objects.requireNonNull(jenkinsContext)
        logLevelType == LogLevelType.WARNING ? this.jenkinsContext.log.warning(logText) : ''
    }

    @SuppressWarnings('GroovyUntypedAccess')
    @NonCPS
    void error(final String logText) {
        Objects.requireNonNull(logText)
        Objects.requireNonNull(logLevelType)
        Objects.requireNonNull(jenkinsContext)
        logLevelType == LogLevelType.ERROR ? this.jenkinsContext.log.error(logText) : ''
    }

}
