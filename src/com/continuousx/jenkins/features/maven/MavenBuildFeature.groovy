package com.continuousx.jenkins.features.maven

interface MavenBuildFeature {
    String prepare()
    String startGoal(String goal)
    String getVersion()
}