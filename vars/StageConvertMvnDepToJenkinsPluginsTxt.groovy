import com.continuousx.jenkins.features.maven.MavenDependenciesToJenkinsPluginsTxt
import com.continuousx.jenkins.pipeline.config.PipelineConfig

def call(PipelineConfig config) {

    stage('Create Jenkins plugins.txt') {
        sh "pwd && ls -la"

        def pomXmlFilename = 'pom.xml'
        def pluginsTxtFilename = 'plugins.txt'
        def pomXml = readMavenPom file: pomXmlFilename
        MavenDependenciesToJenkinsPluginsTxt convert = new MavenDependenciesToJenkinsPluginsTxt()
        String pluginsTxtContent = convert.readPomXmlContent(pomXml)
                .and()
                .convert2PluginsTxt()
                .and().getPluginsTxtContent()
        writeFile file: pluginsTxtFilename, text: pluginsTxtContent
        log.info 'build this'
    }
}