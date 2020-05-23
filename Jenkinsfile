#!/usr/bin/env groovy
import com.continuousx.jenkins.LogLevelType
import com.continuousx.jenkins.pipelines.config.PipelineConfigMavenBuild
import org.jenkinsci.plugins.workflow.libs.Library

@Library(['jenkins-cx-shared-library@feature/new-stages']) _

PipelineConfigMavenBuild pipelineConfig = new PipelineConfigMavenBuild(
        logLevelType: LogLevelType.DEBUG
)

pipelineConfig.configStageJenkinsConvertPluginsTxt(true,true)
pipelineConfig.configStageMavenCompile(true,true)

PipelineGlobal(pipelineConfig)