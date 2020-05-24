import com.continuousx.jenkins.pipelines.PipelineNextLevel
import com.continuousx.jenkins.pipelines.PipelineType
import com.continuousx.jenkins.pipelines.config.PipelineConfig

@SuppressWarnings(['GroovyAssignabilityCheck', 'GroovyVariableCanBeFinal'])
def call(PipelineConfig config) {

    switch(config.getPipelineType()) {
        case PipelineType.PIPELINE_MAVEN_BUILD:
            PipelineMavenBuild(new com.continuousx.jenkins.pipelines.PipelineMavenBuild(this, config))
            break
        case PipelineType.PIPELINE_JENKINS_SHARED_LIB:
            PipelineSharedLib(config)
            break
        case PipelineType.PIPELINE_NEXT_LEVEL:
            log.info "run Pipeline: ${config.getPipelineType()}"
            new PipelineNextLevel(this, config).run()
            break
        default:
            log.warning 'non pipeline type found'
    }
}