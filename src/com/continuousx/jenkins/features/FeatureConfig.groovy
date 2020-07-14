package com.continuousx.jenkins.features

import com.continuousx.jenkins.logger.LogLevelType

interface FeatureConfig extends Serializable {

    FeatureType type
    boolean failOnError = true
    LogLevelType logLevelType = LogLevelType.INFO

}
