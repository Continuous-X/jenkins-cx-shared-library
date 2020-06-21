package com.continuousx.jenkins.features.maven.build

import com.continuousx.jenkins.LogLevelType
import com.continuousx.jenkins.features.FeatureType
import com.continuousx.jenkins.features.maven.AbstractMavenFeature
import com.continuousx.jenkins.features.maven.MavenCommand

class FeatureMavenBuildImpl extends AbstractMavenFeature {

    protected FeatureMavenBuildImpl(final def jenkinsContext, final boolean failOnError, LogLevelType logLevel = LogLevelType.INFO) {
        super(jenkinsContext,
                [],
                failOnError,
                FeatureType.FEATURE_MAVEN_POM_CONVERT_DEP_TO_JENKINS_PLUGINS_TXT,
                logLevel)
    }


    @Override
    void runFeatureImpl() {
        if(checkNeededPlugins()) {
            showVersion()
        } else {
            jenkinsContext.log.error("check needed plugins: ${neededPlugins}")
        }
    }

    @Override
    MavenCommand getCommand() {
        MavenCommand.MVN
    }

    @Override
    String getSettingsXml() {
        ''
    }
}
