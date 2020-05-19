package com.continuousx.jenkins.stages

import com.continuousx.jenkins.LogLevelType
import com.continuousx.jenkins.features.maven.MavenPomFeatureImpl
import com.continuousx.jenkins.stages.config.StageConfigJenkinsConvertPluginsTxt

class StageJenkinsConvertPluginsTxt extends AbstractStage {
    StageJenkinsConvertPluginsTxt(def jenkinsContext, StageConfigJenkinsConvertPluginsTxt config, LogLevelType logLevel = LogLevelType.INFO) {
        super(jenkinsContext, config, logLevel)
    }

    @Override
    void run() {
        new MavenPomFeatureImpl(jenkinsContext, config.logLevelType()).prepare()
                .and()
                .readPomXmlContent()
                .and()
                .writePluginsTxt()
                .and()
                .checkPluginsTxt()
    }
}
