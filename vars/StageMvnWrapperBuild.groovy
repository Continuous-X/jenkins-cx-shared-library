import com.continuousx.jenkins.features.maven.MavenBuildWrapperFeatureImpl
import com.continuousx.jenkins.pipeline.config.PipelineConfig

def call(PipelineConfig config) {

    stage('build') {
        log.info "run build"
        MavenBuildWrapperFeatureImpl maven = new MavenBuildWrapperFeatureImpl(delegate)
        maven.getVersion()
        maven.startGoal('clean install')
    }
}