package com.continuousx.jenkins.features.jenkins.globallib

import com.continuousx.jenkins.features.AbstractFeature
import com.continuousx.utils.jenkins.JenkinsConfig
import org.apache.maven.model.Model
import org.jenkinsci.plugins.workflow.cps.EnvActionImpl
import org.jenkinsci.plugins.workflow.libs.GlobalLibraries
import org.jenkinsci.plugins.workflow.libs.LibraryConfiguration
import org.jenkinsci.plugins.workflow.libs.SCMSourceRetriever

class FeatureJenkinsGlobalLibVersionImpl extends AbstractFeature {

    public static final String PLUGINS_TXT_FILENAME = 'plugins.txt'
    public static final String POM_XML_FILENAME = 'pom.xml'

    private Model model

    @SuppressWarnings('GroovyUntypedAccess')
    protected FeatureJenkinsGlobalLibVersionImpl(final def jenkinsContext, final FeatureJenkinsGlobalLibVersionConfig featureConfig) {
        super(jenkinsContext,
                ['workflow-cps-global-lib'],
                featureConfig)
    }

    @Override
    void runFeatureImpl() {
        EnvActionImpl pipelineEnv = this.jenkinsContext.env
        logger.logInfo "displayName: ${pipelineEnv.getDisplayName()}"
        logger.logInfo "properties: ${pipelineEnv.getProperties().toString()}"
        String sharedLibProperty = "library.${JenkinsConfig.JENKINS_CONFIG_GLOBAL_LIBRARY_JENKINS_CX_SHARED_LIB}.version"
        logger.logInfo "${sharedLibProperty}: ${pipelineEnv.getProperty(sharedLibProperty)}"


        GlobalLibraries globalLibraries = GlobalLibraries.get()
        List<LibraryConfiguration> libraryConfigurationList = globalLibraries.getLibraries()

        LibraryConfiguration libraryConfiguration = libraryConfigurationList.find {it.name == JenkinsConfig.JENKINS_CONFIG_GLOBAL_LIBRARY_JENKINS_CX_SHARED_LIB}
        logger.logInfo "test output 1:  ${((SCMSourceRetriever)libraryConfiguration.getRetriever()).getScm()}"
        logger.logInfo "test output 2: ${libraryConfiguration.getRetriever().getDescriptor()} "
        logger.logInfo "test output 3:  ${sharedLibProperty}"
        logger.logInfo "test output 4:  ${System.getProperty(sharedLibProperty)}"
        logger.logInfo "test output 5:  ${this.jenkinsContext.env.library.${JenkinsConfig.JENKINS_CONFIG_GLOBAL_LIBRARY_JENKINS_CX_SHARED_LIB}.version}"
    }

}
