import com.continuousx.jenkins.features.maven.MavenBuildFeature
import com.continuousx.jenkins.features.maven.MavenBuildWrapperFeatureImpl
import com.continuousx.jenkins.pipeline.config.PipelineConfig

def call(PipelineConfig config) {

    stage('build') {
        log.info "run build"
        assert fileExists(file: MavenBuildWrapperFeatureImpl.MVN_WRAPPER_FILENAME)
        assert fileExists(file: MavenBuildWrapperFeatureImpl.MVN_SETTINGS_XML)

        MavenBuildFeature maven = new MavenBuildWrapperFeatureImpl(this).prepare()
        log.info maven.getVersion()
        maven.startGoal('clean install')

    }
}