import com.continuousx.jenkins.features.maven.MavenPomFeatureImpl
import com.continuousx.jenkins.pipeline.config.PipelineConfig

def call(PipelineConfig config) {

    stage('Create Jenkins plugins.txt') {
        def pomXmlFilename = 'pom.xml'
        def pluginsTxtFilename = 'plugins.txt'
        def pomXml = readFile file: pomXmlFilename
        MavenPomFeatureImpl convert = new MavenPomFeatureImpl()
        String pluginsTxtContent = convert.readPomXmlContent(pomXml.toString())
                .and()
                .convertDependencies2PluginsTxt()
                .and().getPluginsTxtContent()
        writeFile file: pluginsTxtFilename, text: pluginsTxtContent
        log.info 'build this'
    }
}