package com.continuousx.jenkins.stages.jenkins.convertpluginstxt

import com.continuousx.jenkins.logger.LogLevelType
import com.continuousx.jenkins.stages.StageConfig
import com.continuousx.jenkins.stages.StageType
import com.continuousx.utils.github.BranchType

class StageJenkinsConvertPluginsTxtConfig implements StageConfig {

    final static StageType type = StageType.STAGE_JENKINS_CONVERT_PLUGINS_TXT
    LogLevelType logLevelType = LogLevelType.WARNING
    BranchType allowedBranch = BranchType.ALL
    boolean active = true
    boolean failOnError = true

}
