package com.continuousx.jenkins.features

interface FeatureConfig extends Serializable {

    FeatureType getType()
    boolean isFailOnError()

}
