package com.continuousx.jenkins.features.jenkins.globallib

import com.continuousx.jenkins.features.AbstractFeature
import com.continuousx.utils.jenkins.JenkinsConfig
import org.apache.maven.model.Model
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
        GlobalLibraries globalLibraries = GlobalLibraries.get()
        List<LibraryConfiguration> libraryConfigurationList = globalLibraries.getLibraries()

        LibraryConfiguration libraryConfiguration = libraryConfigurationList.find {it.name == JenkinsConfig.JENKINS_CONFIG_GLOBAL_LIBRARY_JENKINS_CX_SHARED_LIB}
        logger.logInfo "test output 1:  ${((SCMSourceRetriever)libraryConfiguration.getRetriever()).getScm().getOwner().getName()}"
        logger.logInfo "test output 2: ${libraryConfiguration.getRetriever().getDescriptor()} "
        String sharedLibProperty = "library.${JenkinsConfig.JENKINS_CONFIG_GLOBAL_LIBRARY_JENKINS_CX_SHARED_LIB}.version"
        logger.logInfo "test output 3:  ${System.getProperty(sharedLibProperty)}"
        logger.logInfo "test output 4:  ${this.jenkinsContext.env.library.${JenkinsConfig.JENKINS_CONFIG_GLOBAL_LIBRARY_JENKINS_CX_SHARED_LIB}.version}"
    }

}
