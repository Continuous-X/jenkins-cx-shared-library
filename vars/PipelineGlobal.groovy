import com.continuousx.jenkins.pipelines.nextlevel.PipelineNextLevel
import com.continuousx.jenkins.pipelines.PipelineType
import com.continuousx.jenkins.pipelines.PipelineConfig

@SuppressWarnings(['GroovyAssignabilityCheck', 'GroovyVariableCanBeFinal'])
def call(PipelineConfig config) {

    switch(config.getType()) {
        case PipelineType.PIPELINE_MAVEN_BUILD:
            PipelineMavenBuild(new com.continuousx.jenkins.pipelines.mavenbuild.PipelineMavenBuild(this, config))
            break
        case PipelineType.PIPELINE_JENKINS_SHARED_LIB:
            PipelineSharedLib(config)
            break
        case PipelineType.PIPELINE_NEXT_LEVEL:
            log.info "run Pipeline: ${config.getType()}"
            new PipelineNextLevel(this, config).run()
            break
        default:
            log.warning 'non pipeline type found'
    }
}