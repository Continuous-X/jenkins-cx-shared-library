package com.continuousx.jenkins.features.maven.build

import com.continuousx.jenkins.features.maven.AbstractMavenFeature
import com.continuousx.jenkins.features.maven.MavenCommand

class FeatureMavenBuildImpl extends AbstractMavenFeature {

    @SuppressWarnings('GroovyUntypedAccess')
    protected FeatureMavenBuildImpl(final def jenkinsContext, final FeatureMavenBuildConfig featureConfig) {
        super(jenkinsContext,
                [],
                featureConfig)
    }


    @SuppressWarnings('GroovyUntypedAccess')
    @Override
    void runFeatureImpl() {
        if(checkNeededPlugins()) {
            showVersion()
        } else {
            jenkinsContext.log.error("check needed plugins: ${neededPlugins}")
        }
    }

    @Override
    MavenCommand getCommand() {
        MavenCommand.MVN
    }

    @Override
    String getSettingsXml() {
        ''
    }

}
