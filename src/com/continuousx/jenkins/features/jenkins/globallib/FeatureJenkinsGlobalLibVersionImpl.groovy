package com.continuousx.jenkins.features.jenkins.globallib

import com.continuousx.jenkins.features.AbstractFeature
import com.continuousx.utils.jenkins.JenkinsConfig
import com.continuousx.utils.jenkins.JenkinsEnv
import org.jenkinsci.plugins.workflow.cps.EnvActionImpl

class FeatureJenkinsGlobalLibVersionImpl extends AbstractFeature {

    @SuppressWarnings('GroovyUntypedAccess')
    protected FeatureJenkinsGlobalLibVersionImpl(final def jenkinsContext, final FeatureJenkinsGlobalLibVersionConfig featureConfig) {
        super(jenkinsContext,
                ['workflow-cps-global-lib'],
                featureConfig)
    }

    @Override
    void runFeatureImpl() {
        EnvActionImpl pipelineEnv = this.jenkinsContext.env
        String sharedLibProperty = "library.${JenkinsConfig.JENKINS_CONFIG_GLOBAL_LIBRARY_JENKINS_CX_SHARED_LIB}.version"
        logger.logInfo "${sharedLibProperty}: ${pipelineEnv.getEnvironment().get(sharedLibProperty)}"
        pipelineEnv.setProperty(JenkinsEnv.CX_SHARED_LIB_BRANCH.toString(), pipelineEnv.getEnvironment().get(sharedLibProperty))
        logger.logInfo "${JenkinsEnv.CX_SHARED_LIB_BRANCH}: ${pipelineEnv.getEnvironment().get(JenkinsEnv.CX_SHARED_LIB_BRANCH.toString())}"
    }

}
