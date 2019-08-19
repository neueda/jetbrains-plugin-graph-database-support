Release should be done manually & carefully.

Be sure to check that plugin works before releasing!
To be executed straight on neueda/jetbrains-plugin-graph-database-support repository master branch.

# Release from Travis
  
1) Push new release tag to Github
    ./gradlew release
2) Credentials are [stored in Travis](https://travis-ci.org/neueda/jetbrains-plugin-graph-database-support/settings#ember7449)
3) Plugin will be automatically deployed to Jetbrains Plugin Registry when new tag is pushed (see `deploy` in [.travis.yml](.travis.yml))

# Release from workstation

Commands:

1) Push new release tag to github
./gradlew release

2) Checkout new release tag locally
git checkout <new-release-tag>

3) Clean everything
./gradlew clean

4) Publish plugin to Jetbrains Plugin Registry
Ensure {HOME}/.gradle/gradle.properties file contains intellij credentials
intellijUsername=
intellijPassword=
Execute
./gradlew buildPlugin
./gradlew :graph-database-support-plugin:publishPlugin