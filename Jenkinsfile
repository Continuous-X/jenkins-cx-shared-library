#!/usr/bin/env groovy
import com.continuousx.jenkins.LogLevelType
import com.continuousx.jenkins.pipelines.config.PipelineConfigMavenBuild
import com.continuousx.jenkins.pipelines.config.PipelineConfigNextLevel
import org.jenkinsci.plugins.workflow.libs.Library

@Library(['jenkins-cx-shared-library@feature/new-stages']) _

PipelineConfigNextLevel pipelineConfig = new PipelineConfigNextLevel(
        logLevelType: LogLevelType.DEBUG
)

pipelineConfig.configStageJenkinsConvertPluginsTxt(true,true)
pipelineConfig.configStageMavenCompile(true,true)

PipelineGlobal(pipelineConfig)