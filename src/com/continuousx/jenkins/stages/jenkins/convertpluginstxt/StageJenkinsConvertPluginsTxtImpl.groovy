package com.continuousx.jenkins.stages.jenkins.convertpluginstxt

import com.continuousx.jenkins.features.maven.dependencies.FeatureMavenDepToJenkinsPluginsTxtImpl
import com.continuousx.jenkins.stages.AbstractStage

class StageJenkinsConvertPluginsTxtImpl extends AbstractStage {

    @SuppressWarnings('GroovyUntypedAccess')
    protected StageJenkinsConvertPluginsTxtImpl(final def jenkinsContext, final StageJenkinsConvertPluginsTxtConfig config) {
        super(jenkinsContext, ["workflow-basic-steps", "maven-plugin"], config)
    }

    @Override
    @SuppressWarnings(['GroovyUntypedAccess', 'GroovyAssignabilityCheck'])
    void runStageImpl() {
        if (checkNeededPlugins()) {
            new FeatureMavenDepToJenkinsPluginsTxtImpl(jenkinsContext, stageConfig.logLevelType).runFeature()
            jenkinsContext.log.info "stage ready"
        } else {
            jenkinsContext.log.error("check needed plugins: ${neededPlugins}")
        }
    }

}
