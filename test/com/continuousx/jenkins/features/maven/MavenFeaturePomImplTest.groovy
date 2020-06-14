package com.continuousx.jenkins.features.maven


import com.continuousx.jenkins.PipelineMock
import spock.lang.Specification

class MavenFeaturePomImplTest extends Specification {

    resource.jenkins.PipelineMock pipelineMock

    def setup() {
        pipelineMock = Mock(resource.jenkins.PipelineMock)
    }

    def "should be create plugins txt from mvn dependencyManagement"() {
        given:
        String testPom = 'test-resources/pom-maven-feature-test.xml'
        String resultPluginsTxtFilename = 'test-resources/plugins-deptest.txt'
        File pomFile = new File(testPom)
        String pomFileContent = pomFile.getText()
        File resultPluginsTxtFile = new File(resultPluginsTxtFilename)
        assert pomFile.exists()
        MavenFeaturePomImpl mvnDep = new MavenFeaturePomImpl(pipelineMock)

        when:
        resultPluginsTxtFile.write(
                mvnDep.readPomXmlContent(pomFileContent)
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
        MavenFeaturePomImpl mvnDep = new MavenFeaturePomImpl(pipelineMock)

        when:
        mvnDep.readPomXmlContent(pomFileContent)

        then:
        assert mvnDep.getParent().getVersion() == expectedParentVersion
    }

}
