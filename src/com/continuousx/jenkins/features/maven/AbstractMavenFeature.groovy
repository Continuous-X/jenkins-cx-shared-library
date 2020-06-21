package com.continuousx.jenkins.features.maven

import com.cloudbees.groovy.cps.NonCPS
import com.continuousx.jenkins.LogLevelType
import com.continuousx.jenkins.features.AbstractFeature
import com.continuousx.jenkins.features.Feature
import com.continuousx.jenkins.features.FeatureType

abstract class AbstractMavenFeature extends AbstractFeature implements MavenFeature {

    @SuppressWarnings(['SerialVersionUID', 'unused'])
    private static final long serialVersionUID = 1234567L

    @SuppressWarnings('GroovyUntypedAccess')
    protected AbstractMavenFeature(
            final def jenkinsContext,
            final List<String> neededPlugins,
            final boolean failOnError,
            final FeatureType featureType,
            LogLevelType logLevel = LogLevelType.WARNING) {
        super(jenkinsContext,
                neededPlugins << 'workflow-basic-steps',
                failOnError,
                featureType,
                logLevel)
    }

    abstract void runFeatureImpl()

    abstract MavenCommand getCommand()

    abstract String getSettingsXml()

    @NonCPS
    @Override
    void startGoal(MavenGoal goal) {
        jenkinsContext.sh script: "${getCommand().toString()} ${goal.toString()} -s ${getSettingsXml()}",
                returnStdout: true
    }

    @Override
    void showVersion() {
        jenkinsContext.sh script: "${getCommand().toString()} --version",
                returnStdout: true
    }

}
