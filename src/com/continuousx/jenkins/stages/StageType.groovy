package com.continuousx.jenkins.stages

import com.cloudbees.groovy.cps.NonCPS

enum StageType {

    STAGE_MAVEN_COMPILE,
    STAGE_MAVEN_PACKAGE,
    STAGE_MAVEN_INSTALL,
    STAGE_MAVEN_DEPLOY,
    STAGE_JENKINS_CONVERT_PLUGINS_TXT,
    STAGE_GITHUB_PROTECTION_CHECK,
    STAGE_SCAN_HOST

    @Override
    @NonCPS
    String toString() {
        name().toLowerCase()
    }

}
