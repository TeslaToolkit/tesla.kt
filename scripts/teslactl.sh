#!/bin/sh
set -e

ROOT_DIR="$(dirname "${0}")/.."
RUN_DIR="${PWD}"
VERSION="$(grep "teslaKotlinVersion" gradle.properties | sed 's/=/ /g' | awk '{print $2}')"
cd "${ROOT_DIR}"
./gradlew -q --console=plain dists:teslactl-fatjar:shadowJar
cd "${RUN_DIR}"
exec java -jar "dists/teslactl-fatjar/build/libs/teslactl-fatjar-${VERSION}-all.jar" "${@}"
