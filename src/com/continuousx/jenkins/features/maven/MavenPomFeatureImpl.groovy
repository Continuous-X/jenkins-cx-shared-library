package com.continuousx.jenkins.features.maven

import org.apache.maven.model.Model
import org.apache.maven.model.Parent
import org.apache.maven.model.io.xpp3.MavenXpp3Reader

class MavenPomFeatureImpl implements MavenPomFeature {

    private static final String PLUGINS_TXT_FILENAME = 'plugins.txt'

    private String pluginsTxtContent
    private Model model

    @Override
    MavenPomFeature readPomXmlContent(String pomXmlContent) {
        Objects.nonNull(pomXmlContent)
        assert pomXmlContent.length() > 0

        MavenXpp3Reader xpp3Reader = new MavenXpp3Reader()
        this.model = xpp3Reader.read(new ByteArrayInputStream( pomXmlContent.getBytes()))

        return this
    }

    @Override
    MavenPomFeature and() {
        return this
    }

    @Override
    MavenPomFeature convertDependencies2PluginsTxt() {
        assert this.model != null

        StringBuilder content = new StringBuilder()
        this.model.getDependencyManagement().getDependencies().each { dependency ->
            content.append(dependency.getArtifactId())
                    .append(':')
                    .append(dependency.getVersion())
                    .append('\n')
        }
        this.pluginsTxtContent = content.toString()

        return this
    }

    @Override
    String getPluginsTxtContent() {
        return this.pluginsTxtContent
    }

    @Override
    Parent getParent() {
        return this.model.getParent()
    }
}
