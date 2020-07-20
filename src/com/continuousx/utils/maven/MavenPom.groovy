package com.continuousx.utils.maven

import com.continuousx.jenkins.features.exceptions.FeatureException
import com.continuousx.jenkins.logger.PipelineLogger
import org.apache.maven.model.Model
import org.apache.maven.model.io.xpp3.MavenXpp3Reader

class MavenPom {

    PipelineLogger logger

    Model getMavenModel(final String pomContent) {
        Objects.requireNonNull(logger)
        Objects.requireNonNull(pomContent)
        try {
            final MavenXpp3Reader xpp3Reader = new MavenXpp3Reader()
            logger.logDebug("convert string to maven pom model:\n${pomContent}")
            return xpp3Reader.read(new ByteArrayInputStream(pomContent.getBytes()))
        } catch (Exception exception) {
            logger.logError("${exception.getMessage()}")
            throw new FeatureException(exception.getMessage())
        }
    }

}
