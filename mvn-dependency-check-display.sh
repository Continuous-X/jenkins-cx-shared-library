#!/bin/sh -e
BASEDIR=$(dirname "$0")

MAVEN_SETTINGS_DEFAULT='.mvn/wrapper/settings.xml'
MAVEN_SETTINGS=${1:-${MAVEN_SETTINGS_DEFAULT}}

./mvnw versions:update-parent -s ${MAVEN_SETTINGS}
./mvnw versions:display-plugin-updates -s ${MAVEN_SETTINGS}
./mvnw versions:display-dependency-updates -s ${MAVEN_SETTINGS}
