package com.continuousx.jenkins.features.maven

import com.cloudbees.groovy.cps.NonCPS
import com.continuousx.jenkins.features.AbstractFeature
import com.continuousx.jenkins.features.FeatureConfig

abstract class AbstractMavenFeature extends AbstractFeature implements MavenFeature {

    @SuppressWarnings(['SerialVersionUID', 'unused'])
    protected static final long serialVersionUID = 6428379462560969571L

    @SuppressWarnings('GroovyUntypedAccess')
    protected AbstractMavenFeature(
            final def jenkinsContext,
            final List<String> neededPlugins,
            final FeatureConfig featureConfig) {
        super(jenkinsContext,
                neededPlugins << 'workflow-basic-steps',
                featureConfig)
    }

    abstract void runFeatureImpl()

    abstract String getCommand()

    abstract String getSettingsXml()

    @SuppressWarnings('GroovyUntypedAccess')
    @NonCPS
    @Override
    void startGoal(final MavenGoal goal) {
        jenkinsContext.sh script: "${getCommand()} ${goal.toString()} ${getSettingsXml()}",
                returnStdout: true
    }

    @SuppressWarnings('GroovyUntypedAccess')
    @Override
    void showVersion() {
        jenkinsContext.sh script: "${getCommand().toString()} --version ${getSettingsXml()}"
    }

}
