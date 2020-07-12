package com.continuousx.jenkins.features.maven.build.wrapper

import com.continuousx.jenkins.features.maven.AbstractMavenFeature
import com.continuousx.jenkins.features.maven.MavenCommand

class FeatureMavenWrapperBuildImpl extends AbstractMavenFeature {

    @SuppressWarnings('GroovyUntypedAccess')
    protected FeatureMavenWrapperBuildImpl(final def jenkinsContext, final FeatureMavenWrapperBuildConfig featureConfig) {
        super(jenkinsContext, [], featureConfig)
    }

    @SuppressWarnings('GroovyUntypedAccess')
    private void setPermission() {
        jenkinsContext.sh(
                script: "ls -la && pwd && chmod 555 ${getCommand()}",
                returnStdout: true)
    }

    @SuppressWarnings('GroovyUntypedAccess')
    @Override
    void runFeatureImpl() {
        if(checkNeededPlugins()) {
            setPermission()
            showVersion()
        } else {
            jenkinsContext.log.error("check needed plugins: ${neededPlugins}")
        }
    }

    @Override
    String getCommand() {
        MavenCommand.MVNW
    }

    @Override
    String getSettingsXml() {
        '-s .mvn/settings.xml'
    }

}
