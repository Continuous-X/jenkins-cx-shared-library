package com.continuousx.jenkins.logger

import com.cloudbees.groovy.cps.NonCPS

class PipelineLogger implements Serializable {

    private def jenkinsContext
    LogLevelType logLevelType = LogLevelType.WARNING

    PipelineLogger(final def jenkinsContext) {
        Objects.requireNonNull(jenkinsContext)
        this.jenkinsContext = jenkinsContext
    }

    @SuppressWarnings('GroovyUntypedAccess')
    @NonCPS
    void logDebug(final String logText) {
        Objects.requireNonNull(logText)
        boolean checked = checkPrint(LogLevelType.DEBUG)
        this.jenkinsContext.echo "log debug: ${checked}"
        checked ? this.jenkinsContext.log.debug(logText) : ''
    }

    @SuppressWarnings('GroovyUntypedAccess')
    @NonCPS
    void logInfo(final String logText) {
        Objects.requireNonNull(logText)
        boolean checked = checkPrint(LogLevelType.INFO)
        this.jenkinsContext.echo "log info: ${checked}"
        checked ? this.jenkinsContext.log.info(logText) : ''
    }

    @SuppressWarnings('GroovyUntypedAccess')
    @NonCPS
    void logWarning(final String logText) {
        Objects.requireNonNull(logText)
        boolean checked = checkPrint(LogLevelType.WARNING)
        this.jenkinsContext.echo "log warning: ${checked}"
        checked ? this.jenkinsContext.log.warning(logText) : ''
    }

    @SuppressWarnings('GroovyUntypedAccess')
    @NonCPS
    void logError(final String logText) {
        Objects.requireNonNull(logText)
        boolean checked = checkPrint(LogLevelType.ERROR)
        this.jenkinsContext.echo "log error: ${checked}"
        checked ? this.jenkinsContext.log.error(logText) : ''
    }

    @SuppressWarnings('GroovyUntypedAccess')
    @NonCPS
    void logFatal(final String logText) {
        Objects.requireNonNull(logText)
        boolean checked = checkPrint(LogLevelType.FATAL)
        this.jenkinsContext.echo "log fatal: ${checked}"
        checked ? this.jenkinsContext.log.fatal(logText) : ''
    }

    @NonCPS
    boolean checkPrint(LogLevelType logLevelType) {
        Objects.requireNonNull(logLevelType)
        if (logLevelType == this.logLevelType)
            return true
        if (this.logLevelType == LogLevelType.DEBUG)
            return true
        if (this.logLevelType == LogLevelType.INFO)
            if (logLevelType == LogLevelType.FATAL || logLevelType == LogLevelType.ERROR || logLevelType == LogLevelType.WARNING)
                return true
        if (this.logLevelType == LogLevelType.WARNING)
            if (logLevelType == LogLevelType.FATAL || logLevelType == LogLevelType.ERROR)
                return true
        if (this.logLevelType == LogLevelType.ERROR)
            if (logLevelType == LogLevelType.FATAL)
                return true
        false
    }
}
