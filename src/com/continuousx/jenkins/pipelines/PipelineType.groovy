package com.continuousx.jenkins.pipelines

import com.cloudbees.groovy.cps.NonCPS

enum PipelineType {

    PIPELINE_JENKINS_SHARED_LIB,
    PIPELINE_MAVEN_BUILD,
    PIPELINE_DOCKER_IMAGE,
    PIPELINE_NEXT_LEVEL

    @NonCPS
    String toString() {
        name().toUpperCase()
    }

}
