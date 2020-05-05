import com.continuousx.jenkins.features.maven.MavenPomFeatureImpl
import com.continuousx.jenkins.pipeline.config.PipelineConfig
import com.continuousx.jenkins.pipeline.exceptions.JenkinsPluginNotInstalledException

def call(PipelineConfig config) {

    stage('Create Jenkins plugins.txt') {
        try {
            def pluginsTxtFilename = MavenPomFeatureImpl.PLUGINS_TXT_FILENAME
            MavenPomFeatureImpl convert = new MavenPomFeatureImpl(this)
            String pluginsTxtContent = convert.readPomXmlContent()
                    .convertDependencies2PluginsTxt()
            writeFile file: pluginsTxtFilename, text: pluginsTxtContent
            log.info "file created: ${pluginsTxtFilename}"
        } catch (JenkinsPluginNotInstalledException e) {
            log.error e.message
        }
    }
}