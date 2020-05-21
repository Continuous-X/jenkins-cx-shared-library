package com.continuousx.jenkins.features.maven

interface MavenFeature {
    MavenFeature startGoal(String goal)
    MavenFeature showVersion()
}