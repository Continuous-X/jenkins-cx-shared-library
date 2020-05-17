package com.continuousx.jenkins.features.maven

import com.continuousx.jenkins.LogLevelType
import com.continuousx.jenkins.features.AbstractFeature
import com.continuousx.jenkins.features.exceptions.FeatureException
import org.apache.maven.model.Model
import org.apache.maven.model.Parent
import org.apache.maven.model.io.xpp3.MavenXpp3Reader

class MavenPomFeatureImpl extends AbstractFeature {

    public static final String PLUGINS_TXT_FILENAME = 'plugins.txt'
    public static final String POM_XML_FILENAME = 'pom.xml'

    private Model model

    MavenPomFeatureImpl(def jenkinsContext, LogLevelType logLevel = LogLevelType.INFO) {
        super(jenkinsContext, [
                "workflow-basic-steps",
                "maven-plugin"
        ],
        logLevel)
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

    MavenPomFeatureImpl readPomXmlContent(String pomContent = loadPomContent()) throws FeatureException {
        Objects.nonNull(pomContent)

        logLevel == LogLevelType.DEBUG ? jenkinsContext.log.debug("pomContent: \n ${pomContent}") : and()
        MavenXpp3Reader xpp3Reader = new MavenXpp3Reader()
        try {
            this.model = xpp3Reader.read(new ByteArrayInputStream(pomContent.getBytes()))
        } catch (Exception e) {
            jenkinsContext.log.error("${e.getMessage()}")
            throw new FeatureException(e.getMessage())
        }

        return this
    }

    MavenPomFeatureImpl writePluginsTxt(String pluginsTxtContent = convertDependencies2PluginsTxt()) {
        jenkinsContext.writeFile( file: PLUGINS_TXT_FILENAME, text: pluginsTxtContent)
        return this
    }

    void checkPluginsTxt() {
        def checkPluginsTxt = jenkinsContext.fileExists( file: MavenPomFeatureImpl.PLUGINS_TXT_FILENAME)
        jenkinsContext.log.info "check ${MavenPomFeatureImpl.PLUGINS_TXT_FILENAME}: ${checkPluginsTxt}"
        jenkinsContext.log.info checkPluginsTxt ? jenkinsContext.readFile(file: MavenPomFeatureImpl.PLUGINS_TXT_FILENAME) : "not exist"
    }

    String convertDependencies2PluginsTxt() {
        Objects.nonNull(model)

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
