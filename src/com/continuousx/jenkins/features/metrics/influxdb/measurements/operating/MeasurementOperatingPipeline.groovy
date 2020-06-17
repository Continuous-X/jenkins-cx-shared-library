package com.continuousx.jenkins.features.metrics.influxdb.measurements.operating

import com.cloudbees.groovy.cps.NonCPS
import com.continuousx.jenkins.pipelines.PipelineType

class MeasurementOperatingPipeline implements MeasurementOperating {

    private final static MeasurementOperatingType TYPE = MeasurementOperatingType.CX_OPERATING_PIPELINES

    private final static String DATA_KEY_DURATION = 'duration'
    private final static String DATA_KEY_PIPELINE_TYPE = 'type'
    private final static String DATA_KEY_GH_ORGANIZATION = 'gh_orga'
    private final static String DATA_KEY_GH_REPOSITORY = 'gh_repo'

    private final static String TAG_KEY_PIPELINE_TYPE = DATA_KEY_PIPELINE_TYPE
    private final static String TAG_KEY_GH_ORGANIZATION = DATA_KEY_GH_ORGANIZATION
    private final static String TAG_KEY_GH_REPOSITORY = DATA_KEY_GH_REPOSITORY

    private Map dataMap = [:]
    private Map dataMapTags = [:]

    @NonCPS
    void setDuration(long duration) {
        dataMap[DATA_KEY_DURATION] = duration
    }

    @NonCPS
    void setPipelineType(PipelineType type) {
        dataMap[DATA_KEY_PIPELINE_TYPE] = type.toString()
        dataMapTags[TAG_KEY_PIPELINE_TYPE] = type.toString()
    }

    @NonCPS
    void setGHOrganization(String ghOrga) {
        Objects.requireNonNull(ghOrga)
        dataMap[DATA_KEY_GH_ORGANIZATION] = ghOrga
        dataMapTags[TAG_KEY_GH_ORGANIZATION] = ghOrga
    }

    @NonCPS
    void setGHRepository(String ghRepo) {
        Objects.requireNonNull(ghRepo)
        dataMap[DATA_KEY_GH_REPOSITORY] = ghRepo
        dataMapTags[TAG_KEY_GH_REPOSITORY] = ghRepo
    }

    @Override
    @NonCPS
    Map getDataMap() {
        dataMap
    }

    @Override
    @NonCPS
    Map getDataMapTags() {
        dataMapTags
    }

    @Override
    @NonCPS
    MeasurementOperatingType getType() {
        TYPE
    }
}
