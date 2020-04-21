package com.continuousx.jenkins.features.maven

import org.apache.maven.model.Model
import org.apache.maven.model.Parent
import org.apache.maven.model.io.xpp3.MavenXpp3Reader

class MavenPomFeatureImpl {

    public static final String PLUGINS_TXT_FILENAME = 'plugins.txt'
    public static final String POM_XML_FILENAME = 'pom.xml'

    private Model model

    MavenPomFeatureImpl readPomXmlContent(String pomXmlContent) {
        Objects.nonNull(pomXmlContent)
        assert pomXmlContent.length() > 0

        MavenXpp3Reader xpp3Reader = new MavenXpp3Reader()
        this.model = xpp3Reader.read(new ByteArrayInputStream( pomXmlContent.getBytes()))

        return this
    }

    MavenPomFeatureImpl and() {
        return this
    }

    String convertDependencies2PluginsTxt() {
        assert this.model != null

        StringBuilder content = new StringBuilder()
        this.model.getDependencyManagement().getDependencies().each { dependency ->
            content.append(dependency.getArtifactId())
                    .append(':')
                    .append(dependency.getVersion())
                    .append('\n')
        }

        return content.toString()
    }

    Parent getParent() {
        return this.model.getParent()
    }
}
