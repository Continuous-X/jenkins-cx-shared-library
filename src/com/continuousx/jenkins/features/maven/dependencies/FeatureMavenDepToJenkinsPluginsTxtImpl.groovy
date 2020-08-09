package com.continuousx.jenkins.features.maven.dependencies

import com.continuousx.jenkins.features.AbstractFeature
import com.continuousx.jenkins.features.exceptions.FeatureException
import com.continuousx.jenkins.logger.PipelineLogger
import com.continuousx.utils.maven.MavenPom
import org.apache.maven.model.Model
import org.apache.maven.model.Parent

class FeatureMavenDepToJenkinsPluginsTxtImpl extends AbstractFeature {

    public static final String PLUGINS_TXT_FILENAME = 'plugins.txt'
    public static final String POM_XML_FILENAME = 'pom.xml'

    private Model model

    @SuppressWarnings('GroovyUntypedAccess')
    protected FeatureMavenDepToJenkinsPluginsTxtImpl(final def jenkinsContext, final FeatureMavenDepToJenkinsPluginsTxtConfig featureConfig, final PipelineLogger logger) {
        super(jenkinsContext,
                ['workflow-basic-steps'],
                featureConfig,
                logger)
    }

    @SuppressWarnings('GroovyUntypedAccess')
    private String loadPomContent() {
        return jenkinsContext.readFile(file: POM_XML_FILENAME)
    }

    @SuppressWarnings('GroovyUntypedAccess')
    FeatureMavenDepToJenkinsPluginsTxtImpl readPomXmlContent(String pomContent = loadPomContent()) throws FeatureException {
        Objects.nonNull(pomContent)
        this.model = new MavenPom(logger: logger).getMavenModel(pomContent)
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
        logger.logInfo "check ${PLUGINS_TXT_FILENAME}: ${checkPluginsTxt}"
        logger.logInfo checkPluginsTxt ? jenkinsContext.readFile(file: PLUGINS_TXT_FILENAME) : "not exist"
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
