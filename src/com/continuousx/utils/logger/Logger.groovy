package com.continuousx.utils.logger

import com.continuousx.jenkins.LogLevelType

class Logger {

    def jenkinsContext
    LogLevelType logLevelType

    @SuppressWarnings('GroovyUntypedAccess')
    void logDebug(final String logText) {
        Objects.requireNonNull(logText)
        Objects.requireNonNull(logLevelType)
        Objects.requireNonNull(jenkinsContext)
        logLevelType == LogLevelType.DEBUG ? this.jenkinsContext.log.debug(logText) : ''
    }

    @SuppressWarnings('GroovyUntypedAccess')
    void logInfo(final String logText) {
        Objects.requireNonNull(logText)
        Objects.requireNonNull(logLevelType)
        Objects.requireNonNull(jenkinsContext)
        logLevelType == LogLevelType.INFO ? this.jenkinsContext.log.info(logText) : ''
    }

    @SuppressWarnings('GroovyUntypedAccess')
    void logWarning(final String logText) {
        Objects.requireNonNull(logText)
        Objects.requireNonNull(logLevelType)
        Objects.requireNonNull(jenkinsContext)
        logLevelType == LogLevelType.WARNING ? this.jenkinsContext.log.warning(logText) : ''
    }

    @SuppressWarnings('GroovyUntypedAccess')
    void logError(final String logText) {
        Objects.requireNonNull(logText)
        Objects.requireNonNull(logLevelType)
        Objects.requireNonNull(jenkinsContext)
        logLevelType == LogLevelType.ERROR ? this.jenkinsContext.log.error(logText) : ''
    }

}
