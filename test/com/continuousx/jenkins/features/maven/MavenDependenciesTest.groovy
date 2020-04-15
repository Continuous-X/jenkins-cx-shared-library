package com.continuousx.jenkins.features.maven

import spock.lang.Specification

class MavenDependenciesTest extends Specification {
    def "read pom.xml"() {
        given:
        String testPom = 'test-resources/pom-deptest.xml'
        String resultPluginsTxtFilename = 'test-resources/plugins-deptest.txt'
        File pomFile = new File(testPom)
        File resultPluginsTxtFile = new File(resultPluginsTxtFilename)
        assert pomFile.exists()
        MavenDependencies mvnDep = new MavenDependencies(pomFile)

        when:
        mvnDep.readPomXml().and().writePluginsTxt(resultPluginsTxtFile)

        then:
        assert resultPluginsTxtFile.readLines().size() == 22

    }
}
