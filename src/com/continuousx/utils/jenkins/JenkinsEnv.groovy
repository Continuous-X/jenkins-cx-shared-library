package com.continuousx.utils.jenkins

import com.cloudbees.groovy.cps.NonCPS

enum JenkinsEnv {

    CX_SHARED_LIB_BRANCH,
    GIT_URL,
    GIT_BRANCH,
    GIT_COMMIT

    @Override
    @NonCPS
    String toString() {
        name()
    }

}
