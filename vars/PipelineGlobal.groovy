import com.continuousx.jenkins.pipelines.PipelineConfig
import com.continuousx.jenkins.pipelines.PipelineType

def call(final PipelineConfig config) {

    switch(config.type) {
        case PipelineType.PIPELINE_MAVEN_BUILD:
            println "tpye: ${config.type} logLevelType: ${config.logLevelType}"
            PipelineMavenBuild(config)
            break
        case PipelineType.PIPELINE_JENKINS_SHARED_LIB:
            PipelineSharedLib(config)
            break
        case PipelineType.PIPELINE_NEXT_LEVEL:
            log.info "run Pipeline: ${config.type}"
            break
        default:
            log.warning "non pipeline type found: ${config.type}"
    }

}
