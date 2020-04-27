import com.continuousx.jenkins.features.maven.MavenBuildFeature
import com.continuousx.jenkins.features.maven.MavenBuildWrapperFeatureImpl
import com.continuousx.jenkins.pipeline.config.PipelineConfig

def call(PipelineConfig config) {

    stage('build') {
        log.info "run build"
        MavenBuildFeature maven = new MavenBuildWrapperFeatureImpl(config.jenkinsContext)
        maven.getVersion()
        maven.startGoal('clean install')
    }
}