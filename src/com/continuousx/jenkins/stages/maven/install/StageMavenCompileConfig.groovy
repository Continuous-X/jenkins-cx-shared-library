package com.continuousx.jenkins.stages.maven.install

import com.continuousx.jenkins.stages.StageConfig
import com.continuousx.jenkins.stages.StageType
import com.continuousx.utils.github.BranchType

class StageMavenCompileConfig implements StageConfig, Serializable {

    public final static StageType type = StageType.STAGE_MAVEN_COMPILE
    public static boolean active = true
    public static boolean failOnError = true
    public static BranchType allowedBranch = BranchType.ALL

}
