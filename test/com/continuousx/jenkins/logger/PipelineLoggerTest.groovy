package com.continuousx.jenkins.logger

import resource.jenkins.PipelineMock
import spock.lang.Specification
import spock.lang.Unroll

class PipelineLoggerTest extends Specification {

    PipelineMock pipelineMock

    def setup() {
        pipelineMock = Mock(PipelineMock)
    }

    @Unroll
    def "should be checkPrintln is '#expectedCheckResult' if type in Logger is '#defineLogLevelType' and call 'log#checkLogLevelType'"() {
        expect:
        final PipelineLogger logger = new PipelineLogger(pipelineMock)
        logger.setLogLevelType(defineLogLevelType)

        assert logger.checkPrint(checkLogLevelType) == expectedCheckResult

        where:
        defineLogLevelType   || checkLogLevelType    || expectedCheckResult
        LogLevelType.DEBUG   || LogLevelType.DEBUG   || true
        LogLevelType.DEBUG   || LogLevelType.INFO    || true
        LogLevelType.DEBUG   || LogLevelType.WARNING || true
        LogLevelType.DEBUG   || LogLevelType.ERROR   || true
        LogLevelType.DEBUG   || LogLevelType.FATAL   || true
        LogLevelType.INFO    || LogLevelType.DEBUG   || false
        LogLevelType.INFO    || LogLevelType.INFO    || true
        LogLevelType.INFO    || LogLevelType.WARNING || true
        LogLevelType.INFO    || LogLevelType.ERROR   || true
        LogLevelType.INFO    || LogLevelType.FATAL   || true
        LogLevelType.WARNING || LogLevelType.DEBUG   || false
        LogLevelType.WARNING || LogLevelType.INFO    || false
        LogLevelType.WARNING || LogLevelType.WARNING || true
        LogLevelType.WARNING || LogLevelType.ERROR   || true
        LogLevelType.WARNING || LogLevelType.FATAL   || true
        LogLevelType.ERROR   || LogLevelType.DEBUG   || false
        LogLevelType.ERROR   || LogLevelType.INFO    || false
        LogLevelType.ERROR   || LogLevelType.WARNING || false
        LogLevelType.ERROR   || LogLevelType.ERROR   || true
        LogLevelType.ERROR   || LogLevelType.FATAL   || true
        LogLevelType.FATAL   || LogLevelType.DEBUG   || false
        LogLevelType.FATAL   || LogLevelType.INFO    || false
        LogLevelType.FATAL   || LogLevelType.WARNING || false
        LogLevelType.FATAL   || LogLevelType.ERROR   || false
        LogLevelType.FATAL   || LogLevelType.FATAL   || true
    }


}
