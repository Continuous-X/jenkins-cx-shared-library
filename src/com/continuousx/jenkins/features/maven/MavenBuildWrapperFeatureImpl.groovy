package com.continuousx.jenkins.features.maven

import com.cloudbees.groovy.cps.NonCPS
import com.continuousx.jenkins.pipeline.utils.JenkinsPluginCheck

class MavenBuildWrapperFeatureImpl implements MavenBuildFeature, Serializable {
    public final static MVN_WRAPPER_FILENAME = 'mvnw'
    public final static MVN_SETTINGS_XML = '.mvn/settings.xml'

    private String mvnwCmd = "./${MVN_WRAPPER_FILENAME}"
    private List<String> neededPlugins = ["workflow-basic-steps", "maven-plugin" ]

    def jenkinsContext

    MavenBuildWrapperFeatureImpl(def jenkinsContext) {
        Objects.nonNull(jenkinsContext)
        this.jenkinsContext = jenkinsContext
    }

    private String setPermissions() {
        return jenkinsContext.sh(
                script: "ls -la && pwd && chmod 555 ${mvnwCmd}",
                returnStdout: true )
    }

    @NonCPS
    @Override
    MavenBuildFeature checkUsage() {
        assert new JenkinsPluginCheck()
                .addPluginList(neededPlugins, jenkinsContext)
                .and()
                .isPluginListInstalled()
        assert jenkinsContext.fileExists(file: MVN_WRAPPER_FILENAME)
        assert jenkinsContext.fileExists(file: MVN_SETTINGS_XML)

        setPermissions()

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
