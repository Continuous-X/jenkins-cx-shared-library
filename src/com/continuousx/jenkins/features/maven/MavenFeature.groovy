package com.continuousx.jenkins.features.maven

interface MavenFeature {
    String startGoal(String goal)
    String getVersion()
}