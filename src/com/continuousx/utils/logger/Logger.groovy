package com.continuousx.utils.logger

import com.cloudbees.groovy.cps.NonCPS
import com.continuousx.jenkins.LogLevelType

class Logger implements Serializable {

    def jenkinsContext
    LogLevelType logLevelType

    @SuppressWarnings('GroovyUntypedAccess')
    @NonCPS
    void logDebug(final String logText) {
        Objects.requireNonNull(logText)
        Objects.requireNonNull(logLevelType)
        Objects.requireNonNull(jenkinsContext)
        logLevelType == LogLevelType.DEBUG ? this.jenkinsContext.log.debug(logText) : ''
    }

    @SuppressWarnings('GroovyUntypedAccess')
    @NonCPS
    void logInfo(final String logText) {
        Objects.requireNonNull(logText)
        Objects.requireNonNull(logLevelType)
        Objects.requireNonNull(jenkinsContext)
        logLevelType == LogLevelType.INFO ? this.jenkinsContext.log.info(logText) : ''
    }

    @SuppressWarnings('GroovyUntypedAccess')
    @NonCPS
    void logWarning(final String logText) {
        Objects.requireNonNull(logText)
        Objects.requireNonNull(logLevelType)
        Objects.requireNonNull(jenkinsContext)
        logLevelType == LogLevelType.WARNING ? this.jenkinsContext.log.warning(logText) : ''
    }

    @SuppressWarnings('GroovyUntypedAccess')
    @NonCPS
    void logError(final String logText) {
        Objects.requireNonNull(logText)
        Objects.requireNonNull(logLevelType)
        Objects.requireNonNull(jenkinsContext)
        logLevelType == LogLevelType.ERROR ? this.jenkinsContext.log.error(logText) : ''
    }

}
