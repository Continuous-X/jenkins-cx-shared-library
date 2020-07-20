package com.continuousx.jenkins.features.github.protection

import com.continuousx.jenkins.logger.LogLevelType
import com.continuousx.jenkins.features.Feature
import com.continuousx.jenkins.features.FeatureConfig
import com.continuousx.jenkins.features.FeatureType
import resource.jenkins.PipelineMock
import spock.lang.Specification
import spock.lang.Unroll

class FeatureGHProtectionCheckImplTest extends Specification {

    PipelineMock pipelineMock
    FeatureGHProtectionCheckBuilder builder

    def setup() {
        pipelineMock = Mock(PipelineMock)
        builder = new FeatureGHProtectionCheckBuilder(pipelineMock)
    }

    def "should be create instance"() {
        given:
        Feature feature = builder.withFeatureConfig(new FeatureGHProtectionCheckConfig()).build()

        when:
        FeatureConfig config = feature.featureConfig

        then:
        assert feature != null
        assert config.type == FeatureType.FEATURE_GH_PROTECTION_CHECK
    }

    @Unroll
    def "should be config Pipeline Object"() {
        expect:
        Feature feature = builder.withFeatureConfig(
                new FeatureGHProtectionCheckConfig(
                        logLevelType: loglevel,
                        failOnError: failOnError
                )
        ).build()


        assert feature != null
        assert feature.featureConfig.logLevelType == expectedLogLevel
        assert feature.featureConfig.failOnError == expectedFailOnError

        where:
        failOnError || loglevel             || expectedLogLevel     || expectedFailOnError
        true        || LogLevelType.DEBUG   || LogLevelType.DEBUG   || true
        true        || LogLevelType.INFO    || LogLevelType.INFO    || true
        false       || LogLevelType.WARNING || LogLevelType.WARNING || false
        false       || LogLevelType.ERROR   || LogLevelType.ERROR   || false
    }

    def 'should be config Feature Object failed'() {
        when:
        Feature feature = new FeatureGHProtectionCheckBuilder(jenkinsInstance).withFeatureConfig(
                new FeatureGHProtectionCheckConfig(
                        logLevelType: loglevel
                )
        ).build()

        assert feature != null

        then:
        thrown(expectedException)

        where:
        loglevel          || jenkinsInstance    || expectedException
        LogLevelType.INFO || null               || NullPointerException
        null              || null               || NullPointerException
    }
}
