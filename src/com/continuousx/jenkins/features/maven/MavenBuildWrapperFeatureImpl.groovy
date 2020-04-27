package com.continuousx.jenkins.features.maven

import com.cloudbees.groovy.cps.NonCPS
import com.continuousx.jenkins.pipeline.utils.JenkinsPluginCheck
import groovy.transform.TupleConstructor

@TupleConstructor(includeFields = true)
class MavenBuildWrapperFeatureImpl implements MavenBuildFeature, Serializable {
    public final static MVN_WRAPPER_FILENAME = 'mvnw'
    public final static MVN_SETTINGS_XML = '.mvn/settings.xml'

    private String mvnwCmd = "./${MVN_WRAPPER_FILENAME}"
    private List<String> neededPlugins = ["workflow-basic-steps", "maven" ]

    def jenkinsContext

    boolean checkNeededPlugins() {
        return new JenkinsPluginCheck()
                .addPluginList(neededPlugins)
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
    String startGoal(String goal) {
        assert isWrapperExist()
        assert checkNeededPlugins()

        return jenkinsContext.sh(
                script: "${mvnwCmd} ${goal}",
                returnStdout: true )
    }

    @NonCPS
    String getVersion() {
        assert isWrapperExist()
        assert checkNeededPlugins()

        return jenkinsContext.sh(
                script: "${mvnwCmd} --version",
                returnStdout: true )

    }
}
