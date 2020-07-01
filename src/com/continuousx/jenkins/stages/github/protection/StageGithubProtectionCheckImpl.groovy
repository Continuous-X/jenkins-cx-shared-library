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
        if (checkNeededPlugins()) {
            StageGithubProtectionCheckConfig stageConfig = getStageConfig() as StageGithubProtectionCheckConfig
            final FeatureGHProtectionCheckImpl ghProtectionCheck = new FeatureGHProtectionCheckBuilder(jenkinsContext)
                    .withFeatureConfig(new FeatureGHProtectionCheckConfig(
                            failOnError:stageConfig.failOnError,
                            logLevelType:stageConfig.logLevelType
                    )).build()
            ghProtectionCheck.runFeature()
        } else {
            jenkinsContext.log.error("check needed plugins: ${neededPlugins}")
        }
    }
}
