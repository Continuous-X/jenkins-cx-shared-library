#!/usr/bin/env groovy
import com.continuousx.jenkins.pipeline.config.PipelineConfig
import com.continuousx.jenkins.pipeline.config.PipelineMetadata
import com.continuousx.jenkins.pipeline.config.PipelineType
import org.jenkinsci.plugins.workflow.libs.Library

@Library(['jenkins-cx-shared-library@dependabot/maven/master/org.spockframework-spock-bom-2.0-M2-groovy-3.0']) _

PipelineConfig pipelineConfig = new PipelineConfig(
        metadata: new PipelineMetadata(
                name: 'jenkins-cx-shared-library',
                type: PipelineType.PIPELINE_JENKINS_SHARED_LIB
        ),
        jenkinsContext: this
)

PipelineGlobal( pipelineConfig )