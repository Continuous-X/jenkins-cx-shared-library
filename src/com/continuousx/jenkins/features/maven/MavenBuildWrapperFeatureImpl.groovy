package com.continuousx.jenkins.features.maven

import com.cloudbees.groovy.cps.NonCPS
import com.continuousx.jenkins.LogLevelType
import com.continuousx.jenkins.features.AbstractFeature

class MavenBuildWrapperFeatureImpl extends AbstractFeature implements MavenBuildFeature {
    public final static MVN_WRAPPER_FILENAME = 'mvnw'
    public final static MVN_SETTINGS_XML = '.mvn/settings.xml'

    private String mvnwCmd = "./${MVN_WRAPPER_FILENAME}"


    MavenBuildWrapperFeatureImpl(def jenkinsContext, LogLevelType logLevel = LogLevelType.INFO) {
        super(jenkinsContext, [
                "workflow-basic-steps",
                "maven-plugin"
        ],
        logLevel)
    }

    MavenBuildWrapperFeatureImpl prepare() {
        jenkinsContext.sh(
                script: "ls -la && pwd && chmod 555 ${mvnwCmd}",
                returnStdout: true )
        return this
    }

    @NonCPS
    @Override
    String startGoal(String goal) {
        return jenkinsContext.sh(
                script: "${mvnwCmd} ${goal} -s ${MVN_SETTINGS_XML}",
                returnStdout: true )
    }

    @Override
    String getVersion() {
        return jenkinsContext.sh(
                script: "${mvnwCmd} --version",
                returnStdout: true )
    }

}
