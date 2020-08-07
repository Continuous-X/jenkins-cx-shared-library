package com.continuousx.jenkins.pipelines.mavenbuild

import com.cloudbees.groovy.cps.NonCPS
import com.continuousx.jenkins.logger.LogLevelType
import com.continuousx.jenkins.pipelines.PipelineConfig
import com.continuousx.jenkins.pipelines.PipelineType
import com.continuousx.jenkins.stages.github.protection.StageGithubProtectionCheckConfig
import com.continuousx.jenkins.stages.maven.install.StageMavenCompileConfig
import com.continuousx.jenkins.stages.scanner.StageScanHostConfig

class PipelineMavenBuildConfig implements PipelineConfig {

    final static PipelineType type = PipelineType.PIPELINE_MAVEN_BUILD
    LogLevelType logLevelType = LogLevelType.WARNING

    StageMavenCompileConfig stageConfigMavenCompile = new StageMavenCompileConfig()
    StageGithubProtectionCheckConfig stageGithubProtectionCheckConfig = new StageGithubProtectionCheckConfig()
    StageScanHostConfig stageScanHostConfig = new StageScanHostConfig()

    PipelineMavenBuildConfig(LogLevelType logLevelType = LogLevelType.WARNING) {
        Objects.requireNonNull(logLevelType)
        this.logLevelType = logLevelType

        this.stageConfigMavenCompile.logLevelType = this.logLevelType
        this.stageGithubProtectionCheckConfig.logLevelType = this.logLevelType
        this.stageScanHostConfig.logLevelType = this.logLevelType
    }

    PipelineMavenBuildConfig configStageGHProtectionCheck(final boolean active, final boolean failOnError) {
        this.stageGithubProtectionCheckConfig.active = active
        this.stageGithubProtectionCheckConfig.failOnError = failOnError
        this
    }

    PipelineMavenBuildConfig configStageScanHost(final boolean active, final boolean failOnError) {
        this.stageScanHostConfig.active = active
        this.stageScanHostConfig.failOnError = failOnError
        this
    }

    @Override
    @NonCPS
    PipelineType getType() {
        return type
    }

    @Override
    @NonCPS
    LogLevelType getLogLevelType() {
        return logLevelType
    }
}
