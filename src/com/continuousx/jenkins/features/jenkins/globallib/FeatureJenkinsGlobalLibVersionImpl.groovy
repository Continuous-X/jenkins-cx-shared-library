package com.continuousx.jenkins.features.jenkins.globallib

import com.continuousx.jenkins.features.AbstractFeature
import com.continuousx.utils.jenkins.JenkinsConfig
import org.apache.maven.model.Model
import org.jenkinsci.plugins.workflow.libs.GlobalLibraries
import org.jenkinsci.plugins.workflow.libs.LibraryConfiguration

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
        logger.logInfo "libConfig: ${libraryConfiguration} "
        logger.logInfo "${JenkinsConfig.JENKINS_CONFIG_GLOBAL_LIBRARY_JENKINS_CX_SHARED_LIB}:  "
        logger.logInfo "${JenkinsConfig.JENKINS_CONFIG_GLOBAL_LIBRARY_JENKINS_CX_SHARED_LIB}:  ${this.jenkinsContext.env.library.${JenkinsConfig.JENKINS_CONFIG_GLOBAL_LIBRARY_JENKINS_CX_SHARED_LIB}.version}"
    }

}
