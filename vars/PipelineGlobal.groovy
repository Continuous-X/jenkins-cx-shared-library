import com.continuousx.jenkins.pipeline.config.PipelineConfig
import com.continuousx.jenkins.pipeline.config.PipelineType

def call(PipelineConfig config) {

    switch(config.metadata.type) {
        case PipelineType.PIPELINE_JENKINS_SHARED_LIB:
            pipelineSharedLib  script: this
            break
        default:
            Log.warning 'non pipeline type found'
    }
}