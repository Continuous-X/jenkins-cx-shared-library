import com.continuousx.jenkins.features.maven.MavenPomFeatureImpl
import com.continuousx.jenkins.pipeline.config.PipelineConfig

def call(PipelineConfig config) {

    stage('Create Jenkins plugins.txt') {
        MavenPomFeatureImpl convert = new MavenPomFeatureImpl(this).prepare()
                .and()
                .readPomXmlContent()
                .and()
                .writePluginsTxt()
        def checkPluginsTxt = fileExists file: MavenPomFeatureImpl.PLUGINS_TXT_FILENAME
        log.info "check ${MavenPomFeatureImpl.PLUGINS_TXT_FILENAME}: ${checkPluginsTxt}"
    }
}