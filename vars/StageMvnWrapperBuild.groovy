import com.continuousx.jenkins.features.maven.MavenFeature
import com.continuousx.jenkins.features.maven.MavenFeatureWrapperImpl
import com.continuousx.jenkins.pipelines.config.PipelineConfigMavenBuild

def call(PipelineConfigMavenBuild config) {

    stage('build') {
        log.info "run build"
        assert fileExists(file: MavenFeatureWrapperImpl.MVN_WRAPPER_FILENAME)
        assert fileExists(file: MavenFeatureWrapperImpl.MVN_SETTINGS_XML)

        MavenFeature maven = new MavenFeatureWrapperImpl(this, config.getLogLevelType()).prepare()
        log.info maven.getVersion()
        maven.startGoal('clean install')

    }
}