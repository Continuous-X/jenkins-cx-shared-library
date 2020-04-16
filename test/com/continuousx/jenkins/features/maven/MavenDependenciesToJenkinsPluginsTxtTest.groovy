package com.continuousx.jenkins.features.maven

import spock.lang.Specification

class MavenDependenciesToJenkinsPluginsTxtTest extends Specification {
    def "read pom.xml"() {
        given:
        String testPom = 'test-resources/pom-deptest.xml'
        String resultPluginsTxtFilename = 'test-resources/plugins-deptest.txt'
        File pomFile = new File(testPom)
        String pomFileContent = pomFile.getText()
        File resultPluginsTxtFile = new File(resultPluginsTxtFilename)
        assert pomFile.exists()
        MavenDependenciesToJenkinsPluginsTxt mvnDep = new MavenDependenciesToJenkinsPluginsTxt()

        when:
        resultPluginsTxtFile.write( mvnDep.readPomXmlContent(pomFileContent).and().convert2PluginsTxt().and().getPluginsTxtContent() )

        then:
        assert resultPluginsTxtFile.readLines().size() == 22

    }
}
