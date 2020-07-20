package com.continuousx.jenkins.features.maven

enum MavenCommand {

    MVN

    public final static String MVNW = './mvnw'

    String toString() {
        name().toLowerCase()
    }

}
