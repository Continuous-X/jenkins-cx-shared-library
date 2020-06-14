package com.continuousx.jenkins.features.metrics.influxdb.operating

import com.continuousx.jenkins.features.metrics.MeasurementType

class MeasurementOperatingFeature extends MeasurementOperating {

    MeasurementOperatingFeature() {
        super(
                type: MeasurementType.MEASUREMENT_CX_OPERATING_FEATURE
        )
    }

    void setFailOnError(boolean failOnError) {
        dataMap['failOnError'] = failOnError
        dataMapTags['failOnError'] = failOnError
    }

}
