package com.continuousx.jenkins.features.maven

import com.cloudbees.groovy.cps.NonCPS
import com.continuousx.jenkins.LogLevelType
import com.continuousx.jenkins.features.AbstractFeature

class MavenFeatureImpl extends AbstractFeature implements MavenFeature {
    public final static MVN_WRAPPER_FILENAME = 'mvn'
    public final static MVN_SETTINGS_XML = '.mvn/settings.xml'

    private String mavenCmd = "${MVN_WRAPPER_FILENAME}"

    MavenFeatureImpl(def jenkinsContext, LogLevelType logLevel = LogLevelType.INFO) {
        super(jenkinsContext, [
                "workflow-basic-steps",
                "maven-plugin"
        ],
        logLevel)
    }

    @NonCPS
    @Override
    MavenFeatureImpl startGoal(String goal) {
        return jenkinsContext.sh(
                script: "${mavenCmd} ${goal} -s ${MVN_SETTINGS_XML}",
                returnStdout: true )
    }

    @Override
    MavenFeatureImpl showVersion() {
        return jenkinsContext.sh(
                script: "${mavenCmd} --version",
                returnStdout: true )
    }

    @Override
    MavenFeatureImpl runImpl() {
        if(checkNeededPlugins()) {
            showVersion()
        } else {
            jenkinsContext.log.error("check needed plugins: ${neededPlugins}")
        }
        return this
    }
}
