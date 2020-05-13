import com.continuousx.jenkins.features.maven.MavenPomFeatureImpl
import com.continuousx.jenkins.pipeline.config.PipelineConfig

def call(PipelineConfig config) {

    stage('Create Jenkins plugins.txt') {
        new MavenPomFeatureImpl(this, config.logLevel).prepare()
                .and()
                .readPomXmlContent()
                .and()
                .writePluginsTxt()

        def checkPluginsTxt = fileExists file: MavenPomFeatureImpl.PLUGINS_TXT_FILENAME
        log.info "check ${MavenPomFeatureImpl.PLUGINS_TXT_FILENAME}: ${checkPluginsTxt}"
        log.info checkPluginsTxt ? readFile( file: MavenPomFeatureImpl.PLUGINS_TXT_FILENAME) : "not exist"
    }
}