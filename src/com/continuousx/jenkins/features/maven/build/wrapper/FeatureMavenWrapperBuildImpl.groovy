package com.continuousx.jenkins.features.maven.build.wrapper

import com.cloudbees.groovy.cps.NonCPS
import com.continuousx.jenkins.LogLevelType
import com.continuousx.jenkins.features.AbstractFeature
import com.continuousx.jenkins.features.FeatureType
import com.continuousx.jenkins.features.maven.MavenFeature
import com.continuousx.jenkins.features.maven.MavenGoal

class FeatureMavenWrapperBuildImpl extends AbstractFeature implements MavenFeature {
    public final static MVN_WRAPPER_FILENAME = 'mvnw'
    public final static MVN_SETTINGS_XML = '.mvn/settings.xml'

    private String mavenCmd = "./${MVN_WRAPPER_FILENAME}"


    protected FeatureMavenWrapperBuildImpl(final def jenkinsContext, final boolean failOnError, LogLevelType logLevel = LogLevelType.INFO) {
        super(jenkinsContext,
                ["workflow-basic-steps", "maven-plugin"],
                failOnError,
                FeatureType.FEATURE_MAVEN_POM_CONVERT_DEP_TO_JENKINS_PLUGINS_TXT,
                logLevel)
    }

    private void setPermission() {
        jenkinsContext.sh(
                script: "ls -la && pwd && chmod 555 ${mavenCmd}",
                returnStdout: true)
    }

    @NonCPS
    @Override
    void startGoal(MavenGoal goal) {
        jenkinsContext.sh script: "${mavenCmd} ${goal} -s ${MVN_SETTINGS_XML}",
                returnStdout: true
    }


    @Override
    void showVersion() {
        jenkinsContext.sh script: "${mavenCmd} --version",
                returnStdout: true
    }

    @Override
    void runFeatureImpl() {
        if(checkNeededPlugins()) {
            setPermission()
            showVersion()
        } else {
            jenkinsContext.log.error("check needed plugins: ${neededPlugins}")
        }
    }
}
