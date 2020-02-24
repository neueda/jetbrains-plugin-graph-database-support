## Release should be done manually & carefully.

Be sure to check that plugin works before releasing!  

<sub>Commands to be executed straight from the root directory of [neueda/jetbrains-plugin-graph-database-support](https://github.com/neueda/jetbrains-plugin-graph-database-support)
repository, on the master branch.</sub>

# Update first-start release notes notification before releasing

Release notes: ./platform/src/main/resources/graphdb/messages/GraphBundle.properties

# So, how?

1. Execute this command: `./gradlew release`
2. When prompted, enter the code of version to be currently released (eg. `2.1.5`)
3. Shortly after, when prompted again, enter the next version code (eg. `2.1.6`)
4. Update release description on [GitHub](https://github.com/neueda/jetbrains-plugin-graph-database-support/releases)
5. Cheer and celebrate!

**That's it!**  

Please note that:
- **Travis.org** is used for publishing
- Plugin is automatically deployed to Jetbrains Plugin Registry when new tag is pushed (see `deploy` in [.travis.yml](.travis.yml))
- The next version code will be added to gradle.properties
  - So you can start working on the next version right away3.
- `[skip ci]` git commit message prefix tells Travis not to execute a build (because unnecessary) 
- JetBrains credentials are [stored in Travis](https://travis-ci.org/neueda/jetbrains-plugin-graph-database-support/settings#ember7449)
  - Currently, only INTELLIJ_TOKEN environment variable is needed for publishing

## Secondary option: manual release
Just for reference. Travis is not used in this case

1. Push new release tag to GitHub (Travis must be disabled)  
`./gradlew release`

2. Checkout new release tag locally  
`git checkout <new-release-tag>`

3. Clean everything  
`./gradlew clean`

4. Publish plugin to Jetbrains Plugin Registry  
4.1. Ensure `{HOME}/.gradle/gradle.properties` file contains valid intellij credentials:  
`intellijUsername=xxxx`  
`intellijPassword=xxxx`  
4.2. Build the plugin:  
`./gradlew buildPlugin`  
4.3. Publish the plugin:  
`./gradlew :graph-database-support-plugin:publishPlugin`  
