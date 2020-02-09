#!/bin/sh -e
BASEDIR=$(dirname "$0")

MAVEN_SETTINGS_DEFAULT='.mvn/wrapper/settings.xml'
MAVEN_SETTINGS=${1:-${MAVEN_SETTINGS_DEFAULT}}

./mvnw clean install -s ${MAVEN_SETTINGS}