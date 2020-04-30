package com.continuousx.jenkins.features.maven

interface MavenBuildFeature {
    MavenBuildFeature checkUsage()
    String startGoal(String goal)
    String getVersion()
}