import com.continuousx.jenkins.features.maven.MavenBuildFeature
import com.continuousx.jenkins.features.maven.build.MavenBuildWrapperFeatureImpl
import com.continuousx.jenkins.pipeline.config.PipelineConfig

def call(PipelineConfig config) {

    stage('build') {
        log.info "run build"
        MavenBuildFeature maven = new MavenBuildWrapperFeatureImpl(delegate)
        maven.getVersion()
        maven.startGoal('clean install')
    }
}