package com.continuousx.jenkins.stages.maven.install

import com.continuousx.jenkins.stages.StageConfig
import com.continuousx.jenkins.stages.StageType
import com.continuousx.utils.github.BranchType

class StageMavenCompileConfig implements StageConfig, Serializable {

    static StageType type = StageType.STAGE_MAVEN_COMPILE
    static boolean active = true
    static boolean failOnError = true
    static BranchType allowedBranch = BranchType.ALL

}
