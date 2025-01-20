#!/bin/bash

get_version() {
    LATEST_TAG=$(git describe --tags --abbrev=0 2>/dev/null || echo "v0.0.0")
    BUILD_NUM=${GITHUB_RUN_NUMBER:-0}

    if [[ $GITHUB_REF == refs/tags/* ]]; then
        echo ${GITHUB_REF#refs/tags/}
    else
        echo "${LATEST_TAG}-b${BUILD_NUM}"
    fi
}

echo $(get_version)