package com.continuousx.jenkins.features.maven

import com.cloudbees.groovy.cps.NonCPS
import com.continuousx.jenkins.pipeline.utils.JenkinsPluginCheck

class MavenBuildWrapperFeatureImpl implements MavenBuildFeature, Serializable {
    public final static MVN_WRAPPER_FILENAME = 'mvnw'
    public final static MVN_SETTINGS_XML = '.mvn/settings.xml'

    private String mvnwCmd = "./${MVN_WRAPPER_FILENAME}"
    private List<String> necessaryPlugins = [ "workflow-basic-steps", "maven" ]

    private def jenkinsContext


    MavenBuildWrapperFeatureImpl(jenkinsContext) {
        Objects.nonNull(jenkinsContext)
        this.jenkinsContext = jenkinsContext

        assert new JenkinsPluginCheck()
                .addPluginList(necessaryPlugins)
                .and()
                .isPluginListInstalled()

        assert isWrapperExist()
    }

    @NonCPS
    private boolean isWrapperExist() {
        boolean wrapperFileExist = jenkinsContext.fileExists MVN_WRAPPER_FILENAME
        boolean settingsFileExist = jenkinsContext.fileExists MVN_SETTINGS_XML

        if( wrapperFileExist && settingsFileExist ) {
            return true
        } else {
            return false
        }
    }

    @NonCPS
    String startGoal(String goal) {
        return jenkinsContext.sh(
                script: "${mvnwCmd} ${goal}",
                returnStdout: true )
    }

    @NonCPS
    String getVersion() {
        return jenkinsContext.sh(
                script: "${mvnwCmd} --version",
                returnStdout: true )

    }
}
