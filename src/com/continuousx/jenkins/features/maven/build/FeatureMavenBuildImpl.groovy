package com.continuousx.jenkins.features.maven.build

import com.continuousx.jenkins.features.maven.AbstractMavenFeature
import com.continuousx.jenkins.features.maven.MavenCommand
import com.continuousx.jenkins.logger.PipelineLogger

class FeatureMavenBuildImpl extends AbstractMavenFeature {

    @SuppressWarnings('GroovyUntypedAccess')
    protected FeatureMavenBuildImpl(final def jenkinsContext, final FeatureMavenBuildConfig featureConfig, final PipelineLogger logger) {
        super(jenkinsContext,
                [],
                featureConfig,
                logger)
    }


    @SuppressWarnings('GroovyUntypedAccess')
    @Override
    void runFeatureImpl() {
        if (checkNeededPlugins()) {
            showVersion()
        } else {
            logger.logError("check needed plugins: ${neededPlugins}")
        }
    }

    @Override
    String getCommand() {
        MavenCommand.MVN.toString()
    }

    @Override
    String getSettingsXml() {
        ''
    }

}
