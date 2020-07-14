package com.continuousx.jenkins.features

import com.continuousx.jenkins.logger.LogLevelType

interface FeatureConfig extends Serializable {

    FeatureType getType()
    LogLevelType getLogLevelType()
    boolean isFailOnError()

}
