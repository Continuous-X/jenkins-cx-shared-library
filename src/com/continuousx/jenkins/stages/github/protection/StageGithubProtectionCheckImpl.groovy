package com.continuousx.jenkins.stages.github.protection

import com.continuousx.jenkins.features.Feature
import com.continuousx.jenkins.features.github.protection.FeatureGHProtectionCheckBuilder
import com.continuousx.jenkins.features.github.protection.FeatureGHProtectionCheckConfig
import com.continuousx.jenkins.features.github.protection.FeatureGHProtectionCheckImpl
import com.continuousx.jenkins.logger.PipelineLogger
import com.continuousx.jenkins.stages.AbstractStage

class StageGithubProtectionCheckImpl extends AbstractStage {

    @SuppressWarnings('GroovyUntypedAccess')
    protected StageGithubProtectionCheckImpl(final def jenkinsContext, final StageGithubProtectionCheckConfig stageConfig, final PipelineLogger logger) {
        super(jenkinsContext, ["workflow-basic-steps", "maven-plugin"], stageConfig, logger)
    }

    @SuppressWarnings('GroovyUntypedAccess')
    @Override
    void runStageImpl() {
        final StageGithubProtectionCheckConfig stageConfigProtectionCheck = stageConfig as StageGithubProtectionCheckConfig
        final Feature ghProtectionCheck = new FeatureGHProtectionCheckBuilder(jenkinsContext)
                .withFeatureConfig(new FeatureGHProtectionCheckConfig(
                        failOnError: stageConfigProtectionCheck.isFailOnError()
                ))
                .withLogger(this.logger)
                .build()
        ghProtectionCheck.runFeature()
    }
}
