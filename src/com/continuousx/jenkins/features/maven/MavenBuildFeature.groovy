package com.continuousx.jenkins.features.maven

interface MavenBuildFeature {
    String prepare()
    boolean checkNeededPlugins()
    String startGoal(String goal)
    String getVersion()
}