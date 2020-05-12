package com.continuousx.jenkins.features.maven

import com.cloudbees.groovy.cps.NonCPS
import com.continuousx.jenkins.features.AbstractFeature
import org.apache.maven.model.Model
import org.apache.maven.model.Parent
import org.apache.maven.model.io.xpp3.MavenXpp3Reader

class MavenPomFeatureImpl extends AbstractFeature {

    public static final String PLUGINS_TXT_FILENAME = 'plugins.txt'
    public static final String POM_XML_FILENAME = 'pom.xml'

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

    MavenPomFeatureImpl and() {
        return this
    }

    private String loadPomContent() {
        return jenkinsContext.readFile(file: POM_XML_FILENAME )
    }

    MavenPomFeatureImpl readPomXmlContent(String pomContent = loadPomContent()) {
        Objects.nonNull(pomContent)
        jenkinsContext.echo "pomContent: ${pomContent}"

        MavenXpp3Reader xpp3Reader = new MavenXpp3Reader()
        this.model = xpp3Reader.read(new ByteArrayInputStream(pomContent.getBytes()))

        return this
    }

    String writePluginsTxt(String pluginsTxtContent = convertDependencies2PluginsTxt()) {
        jenkinsContext.writeFile( file: PLUGINS_TXT_FILENAME, text: pluginsTxtContent)
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
