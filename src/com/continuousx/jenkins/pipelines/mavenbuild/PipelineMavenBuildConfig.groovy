package com.continuousx.jenkins.pipelines.mavenbuild

import com.continuousx.jenkins.LogLevelType
import com.continuousx.jenkins.pipelines.PipelineConfig
import com.continuousx.jenkins.pipelines.PipelineType
import com.continuousx.jenkins.stages.github.protection.StageGithubProtectionCheckConfig
import com.continuousx.jenkins.stages.maven.install.StageMavenCompileConfig
import groovy.transform.TupleConstructor

@TupleConstructor
class PipelineMavenBuildConfig extends PipelineConfig {

    public static PipelineType type = PipelineType.PIPELINE_MAVEN_BUILD

    StageMavenCompileConfig stageConfigMavenCompile = new StageMavenCompileConfig()
    StageGithubProtectionCheckConfig stageGithubProtectionCheckConfig = new StageGithubProtectionCheckConfig()

    PipelineMavenBuildConfig(final LogLevelType logLevelType) {
        this.logLevelType = logLevelType
    }

    PipelineMavenBuildConfig configStageMavenCompile(final boolean active, final boolean failOnError) {
        this.stageConfigMavenCompile.active = active
        this.stageConfigMavenCompile.failOnError = failOnError
        this.stageConfigMavenCompile.logLevelType = this.logLevelType
        this
    }

    PipelineMavenBuildConfig configStageGHProtectionCheck(final boolean active, final boolean failOnError) {
        this.stageGithubProtectionCheckConfig.active = active
        this.stageGithubProtectionCheckConfig.failOnError = failOnError
        this.stageGithubProtectionCheckConfig.logLevelType = this.logLevelType
        this
    }

}
