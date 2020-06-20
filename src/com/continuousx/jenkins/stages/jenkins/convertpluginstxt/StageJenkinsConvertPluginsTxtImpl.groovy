package com.continuousx.jenkins.stages.jenkins.convertpluginstxt

import com.continuousx.jenkins.features.maven.MavenFeaturePomImpl
import com.continuousx.jenkins.stages.AbstractStage

class StageJenkinsConvertPluginsTxtImpl extends AbstractStage {

    @SuppressWarnings('GroovyUntypedAccess')
    protected StageJenkinsConvertPluginsTxtImpl(final def jenkinsContext, final StageJenkinsConvertPluginsTxtConfig config) {
        super(jenkinsContext, ["workflow-basic-steps", "maven-plugin"], config)
    }

    @Override
    @SuppressWarnings('GroovyUntypedAccess')
    void runStageImpl() {
        if (checkNeededPlugins()) {
            new MavenFeaturePomImpl(jenkinsContext, stageConfig.logLevelType()).runFeature()
            jenkinsContext.log.info "stage ready"
        } else {
            jenkinsContext.log.error("check needed plugins: ${neededPlugins}")
        }
    }

}
