package com.continuousx.utils.jenkins

import com.cloudbees.groovy.cps.NonCPS

enum JenkinsEnv {

    CX_SHARED_LIB_BRANCH

    @Override
    String toString() {
        name()
    }
}
