#!/bin/sh
set -e

JAVA_EXE="java"
if [ -n "${JAVA_HOME}" ]
then
  JAVA_EXE="${JAVA_HOME}/bin/java"
fi

ROOT_DIR="$(dirname "${0}")/.."
RUN_DIR="${PWD}"
VERSION="$(grep "teslaKotlinVersion" gradle.properties | sed 's/=/ /g' | awk '{print $2}')"
cd "${ROOT_DIR}"
./gradlew -q --console=plain dists:teslactl:shadowJar
cd "${RUN_DIR}"
exec "${JAVA_EXE}" -jar "dists/teslactl/build/libs/teslactl-${VERSION}-all.jar" "${@}"
