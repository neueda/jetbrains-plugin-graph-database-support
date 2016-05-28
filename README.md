Neo4j - Jetbrains IDE plugin
======================
[![Build Status](https://travis-ci.org/Neueda4j/jetbrains-plugin-neo4j.svg?branch=master)](https://travis-ci.org/Neueda4j/jetbrains-plugin-cypher)
[![ZenHub] (https://raw.githubusercontent.com/ZenHubIO/support/master/zenhub-badge.png)] (https://zenhub.io)

Plugin provides [Neo4j](http://neo4j.com/) database support to **all** Jetbrains IDE's.

**WARNING:** Currently plugin is in alpha-stage.  
Report any bugs, incompatibilities or feature requests by creating issue on Github.

Maintained by [Neueda4j](http://neueda4j.com).

# Features

TODO: add some awesome features!

# Installation

Plugin is *link_to_be_provided* via Jetbrains repositories.

Go to `Preferences` -> `Plugins` -> `Browser repositories...` and search for "Neo4j".
Install plugin and restart your IDE.

# Supported Jetbrains products

* IntelliJ IDEA - **tested**.
* RubyMine
* WebStorm
* PhpStorm
* PyCharm
* AppCode
* Android Studio
* DataGrip
* CLion

Plugin is not tested (yet) with all existing Jetbrains products. However it should work
without any issues.

# Screenshots

# Development

Gradle is used as build system. 

```shell
# Build plugin distribution
./gradlew buildPlugin 

# Run idea in development mode
./gradlew runIdea
```

# License

Copyright © 2016 Dmitry Vrublevsky

Released under the Apache 2.0 License.

