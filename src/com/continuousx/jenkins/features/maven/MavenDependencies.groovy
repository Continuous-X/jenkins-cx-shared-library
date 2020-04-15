package com.continuousx.jenkins.features.maven


import org.apache.maven.model.Dependency
import org.apache.maven.model.Model
import org.apache.maven.model.io.xpp3.MavenXpp3Reader

class MavenDependencies {

    private static final String PLUGINS_TXT_FILENAME = 'plugins.txt'

    private File pomXmlFile
    private File pluginsTxtFile

    private List<Dependency> dependencyList

    public MavenDependencies(File pomXmlFile) {
        Objects.nonNull(pomXmlFile)
        assert pomXmlFile.isFile()

        this.pomXmlFile = pomXmlFile
    }

    MavenDependencies readPomXml() {
        Reader reader = new FileReader(this.pomXmlFile)
        MavenXpp3Reader xpp3Reader = new MavenXpp3Reader()
        Model model = xpp3Reader.read(reader)
        this.dependencyList = model.getDependencyManagement().getDependencies()

        return this
    }

    MavenDependencies and() {
        return this
    }

    MavenDependencies writePluginsTxt(File pluginsTxtFile = new File(PLUGINS_TXT_FILENAME)) {
        this.pluginsTxtFile = pluginsTxtFile
        StringBuilder pluginsTxtContent = new StringBuilder()
        this.dependencyList.each { dependency ->
            String artId = dependency.getArtifactId()
            String version = dependency.getVersion()
            pluginsTxtContent.append(artId).append(':').append(version).append('\n')
        }

        this.pluginsTxtFile.write(pluginsTxtContent.toString())

        return this
    }

}
