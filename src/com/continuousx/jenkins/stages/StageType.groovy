package com.continuousx.jenkins.stages

enum StageType {

    STAGE_MAVEN_COMPILE,
    STAGE_MAVEN_PACKAGE,
    STAGE_MAVEN_INSTALL,
    STAGE_MAVEN_DEPLOY,
    STAGE_JENKINS_CONVERT_PLUGINS_TXT

    @Override
    String toString() {
        name().toLowerCase()
    }

}
