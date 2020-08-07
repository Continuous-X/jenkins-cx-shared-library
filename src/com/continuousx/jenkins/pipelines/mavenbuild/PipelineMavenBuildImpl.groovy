package com.continuousx.jenkins.pipelines.mavenbuild

import com.cloudbees.groovy.cps.NonCPS
import com.continuousx.jenkins.pipelines.AbstractPipeline
import com.continuousx.jenkins.stages.Stage
import com.continuousx.jenkins.stages.github.protection.StageGithubProtectionCheckBuilder
import com.continuousx.jenkins.stages.maven.install.StageMavenCompileBuilder
import com.continuousx.jenkins.stages.scanner.StageScanHostBuilder

class PipelineMavenBuildImpl extends AbstractPipeline {

    Stage stageMavenInstall
    Stage stageGHProtectionCheck
    Stage stageScanHost

    @SuppressWarnings(['GroovyUntypedAccess', 'GroovyVariableCanBeFinal'])
    protected PipelineMavenBuildImpl(
            def jenkinsContext,
            PipelineMavenBuildConfig config) {
        super(jenkinsContext, ["workflow-basic-steps", "maven-plugin"], config)
        stageMavenInstall = new StageMavenCompileBuilder(jenkinsContext)
                .withStageConfig(config.getStageConfigMavenCompile())
                .build()
        stageGHProtectionCheck = new StageGithubProtectionCheckBuilder(jenkinsContext)
                .withStageConfig(config.getStageGithubProtectionCheckConfig())
                .build()
        stageScanHost = new StageScanHostBuilder(jenkinsContext)
                .withStageConfig(config.getStageScanHostConfig())
                .build()
    }

    @Override
    @NonCPS
    void runPipelineImpl() {

    }

}
