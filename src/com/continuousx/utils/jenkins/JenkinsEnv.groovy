package com.continuousx.utils.jenkins

enum JenkinsEnv {

    CX_SHARED_LIB_BRANCH,
    GIT_URL,
    GIT_BRANCH,
    GIT_COMMIT

    @Override
    String toString() {
        name()
    }

}
