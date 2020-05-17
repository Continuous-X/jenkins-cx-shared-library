import com.continuousx.jenkins.features.maven.MavenPomFeatureImpl
import com.continuousx.jenkins.stages.StageConfigJenkinsConvertPluginsTxt

def call(StageConfigJenkinsConvertPluginsTxt config) {

    stage('Create Jenkins plugins.txt') {
        if (config.isActive()) {
            new MavenPomFeatureImpl(this, config.logLevelType()).prepare()
                    .and()
                    .readPomXmlContent()
                    .and()
                    .writePluginsTxt()

            def checkPluginsTxt = fileExists file: MavenPomFeatureImpl.PLUGINS_TXT_FILENAME
            log.info "check ${MavenPomFeatureImpl.PLUGINS_TXT_FILENAME}: ${checkPluginsTxt}"
            log.info checkPluginsTxt ? readFile(file: MavenPomFeatureImpl.PLUGINS_TXT_FILENAME) : "not exist"
        } else {
            log.info "stage '${config.stageType}' is deactivated"
        }
    }
}