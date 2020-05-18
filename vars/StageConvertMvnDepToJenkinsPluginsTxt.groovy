import com.continuousx.jenkins.features.maven.MavenPomFeatureImpl
import com.continuousx.jenkins.stages.StageConfigJenkinsConvertPluginsTxt

def call(StageConfigJenkinsConvertPluginsTxt config) {

    stage('Create Jenkins plugins.txt') {
        config.active ? new MavenPomFeatureImpl(this, config.logLevelType()).prepare()
                .and()
                .readPomXmlContent()
                .and()
                .writePluginsTxt()
                .and()
                .checkPluginsTxt() : log.info( "inactive ${config.getStageType()}" )
    }
}