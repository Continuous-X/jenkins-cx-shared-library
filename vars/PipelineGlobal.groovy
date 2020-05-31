import com.continuousx.jenkins.pipelines.PipelineConfig
import com.continuousx.jenkins.pipelines.PipelineType

@SuppressWarnings(['GroovyAssignabilityCheck', 'GroovyVariableCanBeFinal'])
def call(PipelineConfig config) {

    switch(config.getType()) {
        case PipelineType.PIPELINE_MAVEN_BUILD:
            PipelineMavenBuild(config)
            break
        case PipelineType.PIPELINE_JENKINS_SHARED_LIB:
            PipelineSharedLib(config)
            break
        case PipelineType.PIPELINE_NEXT_LEVEL:
            log.info "run Pipeline: ${config.getType()}"
            break
        default:
            log.warning 'non pipeline type found'
    }
}