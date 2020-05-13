#!/usr/bin/env groovy
import com.continuousx.jenkins.pipeline.config.LogLevelType
import com.continuousx.jenkins.pipeline.config.PipelineConfig
import com.continuousx.jenkins.pipeline.config.PipelineConfigMavenBuild
import org.jenkinsci.plugins.workflow.libs.Library

@Library(['jenkins-cx-shared-library@dependabot/maven/master/org.spockframework-spock-bom-2.0-M2-groovy-3.0']) _

PipelineConfig pipelineConfig = new PipelineConfigMavenBuild(
        logLevelType: LogLevelType.DEBUG
)

PipelineGlobal( pipelineConfig )