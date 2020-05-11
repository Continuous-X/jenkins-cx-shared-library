import com.continuousx.jenkins.features.maven.MavenPomFeatureImpl
import com.continuousx.jenkins.pipeline.config.PipelineConfig

def call(PipelineConfig config) {

    stage('Create Jenkins plugins.txt') {
        def pluginsTxtFilename = MavenPomFeatureImpl.PLUGINS_TXT_FILENAME
        MavenPomFeatureImpl convert = new MavenPomFeatureImpl(this).prepare()
        String pluginsTxtContent = convert.readPomXmlContent()
                .convertDependencies2PluginsTxt()
        log.info "plugin.txt content: ${pluginsTxtContent}"
        writeFile file: pluginsTxtFilename, text: pluginsTxtContent
        log.info "file created: ${pluginsTxtFilename}"
    }
}