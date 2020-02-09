#!/bin/sh -e
BASEDIR=$(dirname "$0")

MAVEN_SETTINGS_DEFAULT='.mvn/wrapper/settings.xml'
GITHUB_USER_DEFAULT='username'
GITHUB_PWD_DEFAULT='xxxxxx'
GITHUB_USER=${1:-${GITHUB_USER_DEFAULT}}
GITHUB_PWD=${2:-${GITHUB_PWD_DEFAULT}}
MAVEN_SETTINGS=${3:-${MAVEN_SETTINGS_DEFAULT}}

MAVEN_PARAMS="-s ${MAVEN_SETTINGS} -e"
#  -Dgithub.username=${GITHUB_USER} -Dgithub.password=${GITHUB_PWD}"

./mvnw -Dmaven.wagon.http.pool=false clean deploy ${MAVEN_PARAMS}