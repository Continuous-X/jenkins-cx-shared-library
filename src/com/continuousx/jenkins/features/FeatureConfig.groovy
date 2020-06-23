package com.continuousx.jenkins.features

import com.continuousx.jenkins.LogLevelType

interface FeatureConfig extends Serializable {

    FeatureType getType()
    boolean isFailOnError()
    LogLevelType getLogLevel()

}
