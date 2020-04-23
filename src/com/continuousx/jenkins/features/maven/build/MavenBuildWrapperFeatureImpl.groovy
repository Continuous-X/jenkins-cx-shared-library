package com.continuousx.jenkins.features.maven.build


import com.continuousx.jenkins.features.maven.MavenBuildFeature

class MavenBuildWrapperFeatureImpl implements MavenBuildFeature {
    public final static MVN_WRAPPER_FILENAME = 'mvnw'
    public final static MVN_SETTINGS_XML = '.mvn/settings.xml'

    private String mvnwCmd = "./${MVN_WRAPPER_FILENAME}"

    private def jenkinsContext


    MavenBuildWrapperFeatureImpl(def jenkinsContext) {
        Objects.nonNull(jenkinsContext)
        this.jenkinsContext = jenkinsContext
        assert isWrapperExist()
    }

    private boolean isWrapperExist() {
        boolean wrapperFileExist = jenkinsContext.fileExists MVN_WRAPPER_FILENAME
        boolean settingsFileExist = jenkinsContext.fileExists MVN_SETTINGS_XML

        if( wrapperFileExist && settingsFileExist ) {
            return true
        } else {
            return false
        }
    }

    String startGoal(String goal) {
        return jenkinsContext.sh(
                script: "${mvnwCmd} ${goal}",
                returnStdout: true )
    }

    String getVersion() {
        return jenkinsContext.sh(
                script: "${mvnwCmd} --version",
                returnStdout: true )

    }
}
