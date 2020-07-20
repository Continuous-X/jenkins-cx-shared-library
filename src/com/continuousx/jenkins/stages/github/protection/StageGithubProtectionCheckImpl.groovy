package com.continuousx.jenkins.stages.github.protection

import com.continuousx.jenkins.features.github.protection.FeatureGHProtectionCheckBuilder
import com.continuousx.jenkins.features.github.protection.FeatureGHProtectionCheckConfig
import com.continuousx.jenkins.features.github.protection.FeatureGHProtectionCheckImpl
import com.continuousx.jenkins.stages.AbstractStage

class StageGithubProtectionCheckImpl extends AbstractStage {

    @SuppressWarnings('GroovyUntypedAccess')
    protected StageGithubProtectionCheckImpl(final def jenkinsContext, final StageGithubProtectionCheckConfig stageConfig) {
        super(jenkinsContext, ["workflow-basic-steps", "maven-plugin"], stageConfig)
    }

    @SuppressWarnings('GroovyUntypedAccess')
    @Override
    void runStageImpl() {
        final StageGithubProtectionCheckConfig stageConfigProtectionCheck = stageConfig as StageGithubProtectionCheckConfig
        final FeatureGHProtectionCheckImpl ghProtectionCheck = new FeatureGHProtectionCheckBuilder(jenkinsContext)
                .withFeatureConfig(new FeatureGHProtectionCheckConfig(
                        failOnError: stageConfigProtectionCheck.failOnError,
                        logLevelType: stageConfigProtectionCheck.logLevelType
                )).build()
        ghProtectionCheck.runFeature()
    }
}
