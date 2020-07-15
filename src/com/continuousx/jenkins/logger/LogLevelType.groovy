package com.continuousx.jenkins.logger

import com.cloudbees.groovy.cps.NonCPS

enum LogLevelType {

    DEBUG,
    INFO,
    WARNING,
    ERROR,
    FATAL

    @NonCPS
    String toString() {
        name().toUpperCase()
    }

}
