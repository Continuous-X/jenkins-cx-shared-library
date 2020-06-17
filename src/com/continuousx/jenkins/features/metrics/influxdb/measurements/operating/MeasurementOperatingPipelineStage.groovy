package com.continuousx.jenkins.features.metrics.influxdb.measurements.operating

import com.cloudbees.groovy.cps.NonCPS
import com.continuousx.jenkins.stages.StageType

class MeasurementOperatingPipelineStage implements MeasurementOperating {

    private final static MeasurementOperatingType TYPE = MeasurementOperatingType.CX_OPERATING_STAGES

    private final static String DATA_KEY_DURATION = 'duration'
    private final static String DATA_KEY_STAGE_FAIL_ONERROR = 'fail_onerror'
    private final static String DATA_KEY_STAGE_ACTIVE = 'active'
    private final static String DATA_KEY_STAGE_TYPE = 'type'
    private final static String DATA_KEY_GH_ORGANIZATION = 'gh_orga'
    private final static String DATA_KEY_GH_REPOSITORY = 'gh_repo'

    private final static String TAG_KEY_STAGE_FAIL_ONERROR = DATA_KEY_STAGE_FAIL_ONERROR
    private final static String TAG_KEY_STAGE_ACTIVE = DATA_KEY_STAGE_ACTIVE
    private final static String TAG_KEY_STAGE_TYPE = DATA_KEY_STAGE_TYPE
    private final static String TAG_KEY_GH_ORGANIZATION = DATA_KEY_GH_ORGANIZATION
    private final static String TAG_KEY_GH_REPOSITORY = DATA_KEY_GH_REPOSITORY

    private Map dataMap = [:]
    private Map dataMapTags = [:]

    @NonCPS
    void setDuration(long duration) {
        dataMap[DATA_KEY_DURATION] = duration
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

    @NonCPS
    void setFailOnError(boolean failOnError) {
        dataMap[DATA_KEY_STAGE_FAIL_ONERROR] = failOnError
        dataMapTags[TAG_KEY_STAGE_FAIL_ONERROR] = failOnError.toString()
    }

    @NonCPS
    void setActive(boolean active) {
        dataMap[DATA_KEY_STAGE_ACTIVE] = active
        dataMapTags[TAG_KEY_STAGE_ACTIVE] = active.toString()
    }

    @NonCPS
    void setStageType(StageType type) {
        dataMap[DATA_KEY_STAGE_TYPE] = type.toString()
        dataMapTags[TAG_KEY_STAGE_TYPE] = type.toString()
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
