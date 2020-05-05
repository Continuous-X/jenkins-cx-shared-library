package com.continuousx.jenkins.pipeline.exceptions

class JenkinsPluginNotInstalledException extends Exception {
    JenkinsPluginNotInstalledException(String message) {
        super(message)
    }
}
