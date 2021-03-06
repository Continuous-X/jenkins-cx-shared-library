package com.continuousx.jenkins.features.maven

import com.continuousx.jenkins.logger.LogLevelType
import com.continuousx.jenkins.features.maven.dependencies.FeatureMavenDepToJenkinsPluginsTxtBuilder
import com.continuousx.jenkins.features.maven.dependencies.FeatureMavenDepToJenkinsPluginsTxtConfig
import com.continuousx.jenkins.features.maven.dependencies.FeatureMavenDepToJenkinsPluginsTxtImpl
import resource.jenkins.PipelineMock
import spock.lang.Specification
import spock.lang.Unroll

class FeatureMavenDepToJenkinsPluginsTxtImplTest extends Specification {

    PipelineMock pipelineMock
    FeatureMavenDepToJenkinsPluginsTxtBuilder featureBuilder

    def setup() {
        pipelineMock = Mock(PipelineMock)
        featureBuilder = new FeatureMavenDepToJenkinsPluginsTxtBuilder(pipelineMock)
    }

    def "should be create plugins txt from mvn dependencyManagement"() {
        given:
        String testPom = 'test-resources/pom-maven-feature-test.xml'
        String resultPluginsTxtFilename = 'test-resources/plugins-deptest.txt'
        File pomFile = new File(testPom)
        String pomFileContent = pomFile.getText()
        File resultPluginsTxtFile = new File(resultPluginsTxtFilename)
        assert pomFile.exists()
        FeatureMavenDepToJenkinsPluginsTxtImpl feature = featureBuilder.build()

        when:
        resultPluginsTxtFile.write(
                feature.readPomXmlContent(pomFileContent)
                        .convertDependencies2PluginsTxt()
        )

        then:
        assert resultPluginsTxtFile.readLines().size() == 22
    }

    def "should be get version from parent pom"() {
        given:
        String expectedParentVersion = '4.1'
        String testPom = 'test-resources/pom-maven-feature-test.xml'
        File pomFile = new File(testPom)
        String pomFileContent = pomFile.getText()
        FeatureMavenDepToJenkinsPluginsTxtImpl feature = featureBuilder.build()

        when:
        feature.readPomXmlContent(pomFileContent)

        then:
        assert feature.getParent().getVersion() == expectedParentVersion
    }

    @Unroll
    def "should be config Stage Object"() {
        expect:
        FeatureMavenDepToJenkinsPluginsTxtImpl feature = featureBuilder
                .withFeatureConfig(
                        new FeatureMavenDepToJenkinsPluginsTxtConfig(
                                failOnError: failOnError
                        )
                )
                .build()

        assert feature != null

        assert feature.featureConfig.failOnError == expectedFailOnError

        where:
        failOnError || expectedFailOnError
        true        || true
        false       || false
        true        || true
        true        || true
        true        || true
    }

    def 'should be config Feature Object failed'() {
        when:
        FeatureMavenDepToJenkinsPluginsTxtImpl feature = featureBuilder
                .withFeatureConfig(
                        featureConfig
                ).build()

        assert feature != null

        then:
        thrown(expectedException)

        where:
        featureConfig || expectedException
        null          || NullPointerException
    }

}
