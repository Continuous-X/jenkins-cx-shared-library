package com.continuousx.jenkins.features.maven

interface MavenBuildFeature {
    String startGoal(String goal)
    String getVersion()
}
