package com.continuousx.jenkins.stages.maven.install

import com.continuousx.jenkins.stages.StageConfig
import com.continuousx.jenkins.stages.StageType
import com.continuousx.utils.github.BranchType

class StageMavenCompileConfig implements StageConfig, Serializable {

    final static StageType type = StageType.STAGE_MAVEN_COMPILE
    final static boolean active = true
    final static boolean failOnError = true
    final static BranchType allowedBranch = BranchType.ALL

}
