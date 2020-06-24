package com.continuousx.utils.github

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