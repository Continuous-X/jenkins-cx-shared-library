package com.continuousx.jenkins.features.maven

enum MavenGoal {

    COMPILE,
    INSTALL

    String toString() {
        name().toLowerCase()
    }

}