package com.continuousx.jenkins.features.maven

enum MavenCommand {

    MVN

    String toString() {
        name().toLowerCase()
    }

}
