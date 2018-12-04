#!/usr/bin/env bash
if [ "$TRAVIS_SECURE_ENV_VARS" = false ]; then
    echo "Will not release and publish, no credentials. Maybe you are trying to publish from a fork?";
    exit 0;
fi
./gradlew buildPlugin
./gradlew :graph-database-support-plugin:publishPlugin