package com.continuousx.jenkins.features.maven

import com.cloudbees.groovy.cps.NonCPS
import com.continuousx.jenkins.pipeline.utils.JenkinsPluginCheck

class MavenBuildWrapperFeatureImpl implements MavenBuildFeature, Serializable {
    public final static MVN_WRAPPER_FILENAME = 'mvnw'
    public final static MVN_SETTINGS_XML = '.mvn/settings.xml'

    private String mvnwCmd = "./${MVN_WRAPPER_FILENAME}"
    private List<String> neededPlugins = ["workflow-basic-steps", "maven" ]

    def jenkinsContext

    MavenBuildWrapperFeatureImpl(def jenkinsContext) {
        Objects.nonNull(jenkinsContext)
        this.jenkinsContext = jenkinsContext

        assert checkNeededPlugins()
        //assert isWrapperExist()
    }

    String setPermissions() {
        return jenkinsContext.sh(
                script: "ls -la && pwd && chmod 555 ${mvnwCmd}",
                returnStdout: true )

    }

    @NonCPS
    boolean checkNeededPlugins() {
        return new JenkinsPluginCheck()
                .addPluginList(neededPlugins, jenkinsContext)
                .and()
                .isPluginListInstalled()
    }

    @NonCPS
    boolean isWrapperExist() {
        boolean wrapperFileExist = jenkinsContext.fileExists(file: MVN_WRAPPER_FILENAME)
        boolean settingsFileExist = jenkinsContext.fileExists(file: MVN_SETTINGS_XML)

        if( wrapperFileExist && settingsFileExist ) {
            return true
        } else {
            return false
        }
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
