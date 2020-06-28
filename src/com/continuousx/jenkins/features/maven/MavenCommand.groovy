package com.continuousx.jenkins.features.maven

enum MavenCommand {

    MVN,
    MVNW

    String toString() {
        name().toLowerCase()
    }

}
