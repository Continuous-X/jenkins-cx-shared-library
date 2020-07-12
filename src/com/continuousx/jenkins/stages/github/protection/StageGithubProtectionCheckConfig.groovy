package com.continuousx.jenkins.stages.github.protection

import com.continuousx.jenkins.stages.StageConfig
import com.continuousx.jenkins.stages.StageType

class StageGithubProtectionCheckConfig implements StageConfig, Serializable {

    final static StageType type = StageType.STAGE_MAVEN_COMPILE

}
