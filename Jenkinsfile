#!/usr/bin/env groovy
import com.continuousx.jenkins.LogLevelType
import com.continuousx.jenkins.pipelines.mavenbuild.PipelineMavenBuildConfig
import org.jenkinsci.plugins.workflow.libs.Library

@Library(['jenkins-cx-shared-library@feature/new-stages']) _

PipelineGlobal(new PipelineMavenBuildConfig(logLevelType: LogLevelType.DEBUG)
        .configStageJenkinsConvertPluginsTxt(true,true)
        .configStageMavenCompile(true,true)
)