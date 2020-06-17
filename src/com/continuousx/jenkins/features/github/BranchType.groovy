package com.continuousx.jenkins.features.github

import com.cloudbees.groovy.cps.NonCPS

enum BranchType {

    NOT_MASTER,
    MASTER,
    ALL

    @Override
    @NonCPS
    String toString() {
        name().toLowerCase()
    }
}