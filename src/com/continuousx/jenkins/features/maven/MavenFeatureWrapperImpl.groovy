package com.continuousx.jenkins.features.maven

import com.cloudbees.groovy.cps.NonCPS
import com.continuousx.jenkins.LogLevelType
import com.continuousx.jenkins.features.AbstractFeature

class MavenFeatureWrapperImpl extends AbstractFeature implements MavenFeature {
    public final static MVN_WRAPPER_FILENAME = 'mvnw'
    public final static MVN_SETTINGS_XML = '.mvn/settings.xml'

    private String mavenCmd = "./${MVN_WRAPPER_FILENAME}"


    MavenFeatureWrapperImpl(def jenkinsContext, LogLevelType logLevel = LogLevelType.INFO) {
        super(jenkinsContext, [
                "workflow-basic-steps",
                "maven-plugin"
        ],
                logLevel)
    }

    private void setPermission() {
        jenkinsContext.sh(
                script: "ls -la && pwd && chmod 555 ${mavenCmd}",
                returnStdout: true)
    }

    @NonCPS
    @Override
    MavenFeatureWrapperImpl startGoal(String goal) {
        jenkinsContext.sh(
                script: "${mavenCmd} ${goal} -s ${MVN_SETTINGS_XML}",
                returnStdout: true)
        return this
    }


    @Override
    MavenFeatureWrapperImpl showVersion() {
        jenkinsContext.sh(
                script: "${mavenCmd} --version",
                returnStdout: true)
        return this
    }

    @Override
    MavenFeatureWrapperImpl runImpl() {
        if(checkNeededPlugins()) {
            setPermission()
            showVersion()
        } else {
            jenkinsContext.log.error("check needed plugins: ${neededPlugins}")
        }
        return this
    }
}
