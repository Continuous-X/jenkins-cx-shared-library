import com.continuousx.jenkins.features.maven.MavenPomFeatureImpl
import com.continuousx.jenkins.stages.StageConfigJenkinsConvertPluginsTxt

def call(StageConfigJenkinsConvertPluginsTxt config) {
    if( config.isActive() ) {
        stage('Create Jenkins plugins.txt') {
            new MavenPomFeatureImpl(this, config.logLevelType()).prepare()
                    .and()
                    .readPomXmlContent()
                    .and()
                    .writePluginsTxt()
                    .and()
                    .checkPluginsTxt()
        }
    } else {
        log.info "inactive"
    }
}