#!/usr/bin/env bash
if [ !${TRAVIS_SECURE_ENV_VARS} ]; then
    echo "Will not release and publish, no credentials. Maybe you are trying to publish from a fork?";
    exit 0;
fi
./gradlew clean test
./gradlew buildPlugin
./gradlew :graph-database-support-plugin:publishPlugin