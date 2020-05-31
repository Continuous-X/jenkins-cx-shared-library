package com.continuousx.jenkins.stages.jenkins.convertpluginstxt

import com.continuousx.jenkins.LogLevelType
import com.continuousx.jenkins.features.maven.MavenFeaturePomImpl
import com.continuousx.jenkins.stages.AbstractStage

class StageJenkinsConvertPluginsTxtImpl extends AbstractStage {

    @SuppressWarnings('GroovyUntypedAccess')
    protected StageJenkinsConvertPluginsTxtImpl(final def jenkinsContext, final StageJenkinsConvertPluginsTxtConfig config, final LogLevelType logLevel = LogLevelType.INFO) {
        super(jenkinsContext,
                config, [
                    "workflow-basic-steps",
                    "maven-plugin"
                ],
                logLevel)
    }

    @Override
    @SuppressWarnings('GroovyUntypedAccess')
    void runStage() {
        if (checkNeededPlugins()) {
            new MavenFeaturePomImpl(m_jenkinsContext, m_config.logLevelType()).run()
            m_jenkinsContext.log.info "stage ready"
        } else {
            m_jenkinsContext.log.error("check needed plugins: ${m_neededPlugins}")
        }
    }
}
