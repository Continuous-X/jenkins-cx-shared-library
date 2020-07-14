package com.continuousx.jenkins.features.maven.dependencies

import com.continuousx.jenkins.logger.LogLevelType
import com.continuousx.jenkins.features.AbstractFeature
import com.continuousx.jenkins.features.exceptions.FeatureException
import org.apache.maven.model.Model
import org.apache.maven.model.Parent
import org.apache.maven.model.io.xpp3.MavenXpp3Reader

class FeatureMavenDepToJenkinsPluginsTxtImpl extends AbstractFeature {

    public static final String PLUGINS_TXT_FILENAME = 'plugins.txt'
    public static final String POM_XML_FILENAME = 'pom.xml'

    private Model model

    @SuppressWarnings('GroovyUntypedAccess')
    protected FeatureMavenDepToJenkinsPluginsTxtImpl(final def jenkinsContext, final FeatureMavenDepToJenkinsPluginsTxtConfig featureConfig) {
        super(jenkinsContext,
                ['workflow-basic-steps'],
                featureConfig)
    }

    @SuppressWarnings('GroovyUntypedAccess')
    private String loadPomContent() {
        return jenkinsContext.readFile(file: POM_XML_FILENAME)
    }

    @SuppressWarnings('GroovyUntypedAccess')
    FeatureMavenDepToJenkinsPluginsTxtImpl readPomXmlContent(String pomContent = loadPomContent()) throws FeatureException {
        Objects.nonNull(pomContent)
        if (logLevel == !LogLevelType.DEBUG) {
            jenkinsContext.log.debug("pomContent: \n ${pomContent}")
        }
        final MavenXpp3Reader xpp3Reader = new MavenXpp3Reader()
        try {
            this.model = xpp3Reader.read(new ByteArrayInputStream(pomContent.getBytes()))
        } catch (Exception exception) {
            jenkinsContext.log.error("${exception.getMessage()}")
            throw new FeatureException(exception.getMessage())
        }
        this
    }

    @SuppressWarnings('GroovyUntypedAccess')
    void writePluginsTxt(String pluginsTxtContent = convertDependencies2PluginsTxt()) {
        Objects.requireNonNull(pluginsTxtContent)
        jenkinsContext.writeFile file: PLUGINS_TXT_FILENAME, text: pluginsTxtContent
    }

    @SuppressWarnings('GroovyUntypedAccess')
    void checkPluginsTxt() {
        def checkPluginsTxt = jenkinsContext.fileExists(file: PLUGINS_TXT_FILENAME)
        jenkinsContext.log.info "check ${PLUGINS_TXT_FILENAME}: ${checkPluginsTxt}"
        jenkinsContext.log.info checkPluginsTxt ? jenkinsContext.readFile(file: PLUGINS_TXT_FILENAME) : "not exist"
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

        content.toString()
    }

    Parent getParent() {
        this.model.getParent()
    }

    @Override
    void runFeatureImpl() {
        readPomXmlContent()
        writePluginsTxt()
        checkPluginsTxt()
    }

}
