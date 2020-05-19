import com.continuousx.jenkins.features.maven.MavenBuildFeature
import com.continuousx.jenkins.features.maven.MavenBuildWrapperFeatureImpl
import com.continuousx.jenkins.pipelines.config.PipelineConfigMavenBuild

def call(PipelineConfigMavenBuild config) {

    stage('build') {
        log.info "run build"
        assert fileExists(file: MavenBuildWrapperFeatureImpl.MVN_WRAPPER_FILENAME)
        assert fileExists(file: MavenBuildWrapperFeatureImpl.MVN_SETTINGS_XML)

        MavenBuildFeature maven = new MavenBuildWrapperFeatureImpl(this, config.getLogLevelType()).prepare()
        log.info maven.getVersion()
        maven.startGoal('clean install')

    }
}