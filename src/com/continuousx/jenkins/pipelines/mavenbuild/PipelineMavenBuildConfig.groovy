package com.continuousx.jenkins.pipelines.mavenbuild

import com.continuousx.jenkins.LogLevelType
import com.continuousx.jenkins.pipelines.PipelineConfig
import com.continuousx.jenkins.pipelines.PipelineType
import com.continuousx.jenkins.stages.github.protection.StageGithubProtectionCheckConfig
import com.continuousx.jenkins.stages.maven.install.StageMavenCompileConfig

class PipelineMavenBuildConfig implements PipelineConfig {

    final static PipelineType type = PipelineType.PIPELINE_MAVEN_BUILD
    LogLevelType logLevelType = LogLevelType.WARNING

    StageMavenCompileConfig stageConfigMavenCompile = new StageMavenCompileConfig()
    StageGithubProtectionCheckConfig stageGithubProtectionCheckConfig = new StageGithubProtectionCheckConfig()

    PipelineMavenBuildConfig(final LogLevelType logLevelType = LogLevelType.WARNING) {
        Objects.requireNonNull(logLevelType)
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

    @Override
    PipelineType getType() {
        return type
    }

    @Override
    LogLevelType getLogLevelType() {
        return null
    }
}
