package com.continuousx.jenkins.features.exceptions

class FeatureException extends Exception implements Serializable {
    FeatureException(String message) {
        super(message)
    }
}
