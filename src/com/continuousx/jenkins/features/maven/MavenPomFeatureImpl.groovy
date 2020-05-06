package com.continuousx.jenkins.features.maven

import com.cloudbees.groovy.cps.NonCPS
import com.continuousx.jenkins.features.AbstractFeature
import org.apache.maven.model.Model
import org.apache.maven.model.Parent
import org.apache.maven.model.io.xpp3.MavenXpp3Reader

class MavenPomFeatureImpl extends AbstractFeature {

    public static final String PLUGINS_TXT_FILENAME = 'plugins.txt'
    public static final String POM_XML_FILENAME = 'pom.xml'

    private String pomContent

    private Model model

    MavenPomFeatureImpl(def jenkinsContext) {
        super(jenkinsContext, [
                "workflow-basic-steps",
                "maven-plugin"
        ])
    }

    MavenPomFeatureImpl prepare() {
        return this
    }

    MavenPomFeatureImpl readPomXmlContent() {
        pomContent = jenkinsContext.readFile(file: POM_XML_FILENAME )
        jenkinsContext.echo "pomContent: ${pomContent}"


        MavenXpp3Reader xpp3Reader = new MavenXpp3Reader()
        this.model = xpp3Reader.read(new ByteArrayInputStream(pomContent.getBytes()))

        return this
    }

    String convertDependencies2PluginsTxt() {
        Objects.nonNull(model)

        jenkinsContext.echo "readMvnModel: ${model}"
        StringBuilder content = new StringBuilder()
        model.getDependencyManagement().getDependencies().each { dependency ->
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
