package com.continuousx.jenkins.features.maven


import org.apache.maven.model.Dependency
import org.apache.maven.model.Model
import org.apache.maven.model.io.xpp3.MavenXpp3Reader

class MavenDependenciesToJenkinsPluginsTxt {

    private static final String PLUGINS_TXT_FILENAME = 'plugins.txt'

    private String pluginsTxtContent

    private List<Dependency> dependencyList

    MavenDependenciesToJenkinsPluginsTxt readPomXmlContent(String pomXmlContent) {
        Objects.nonNull(pomXmlContent)
        assert pomXmlContent.length() > 0

        MavenXpp3Reader xpp3Reader = new MavenXpp3Reader()
        Model model = xpp3Reader.read(new ByteArrayInputStream( pomXmlContent.getBytes()))
        this.dependencyList = model.getDependencyManagement().getDependencies()

        return this
    }

    MavenDependenciesToJenkinsPluginsTxt and() {
        return this
    }

    MavenDependenciesToJenkinsPluginsTxt convert2PluginsTxt() {
        assert dependencyList != null

        StringBuilder content = new StringBuilder()
        this.dependencyList.each { dependency ->
            content.append(dependency.getArtifactId())
                    .append(':')
                    .append(dependency.getVersion())
                    .append('\n')
        }
        pluginsTxtContent = content.toString()

        return this
    }

    String getPluginsTxtContent() {
        return pluginsTxtContent
    }

}
