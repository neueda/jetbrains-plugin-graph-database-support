# [Graph Database support](https://github.com/neueda/jetbrains-plugin-graph-database-support)
[![Build Status](https://travis-ci.org/neueda/jetbrains-plugin-graph-database-support.svg?branch=master)](https://travis-ci.org/neueda/jetbrains-plugin-graph-database-support)

Graph Database support plugin allows you to work with databases without leaving IDE.

- Bugs, questions, suggestions - [Issue tracker](https://github.com/neueda/jetbrains-plugin-graph-database-support/issues)
- [Documentation](https://neueda.gitbooks.io/jetbrains-plugin-graph-database-support/content)

Plugin is developed and supported by [Neueda Technologies](http://technologies.neueda.com/).

![plugin screenshot](docs/screenshots/plugin.png)

## Installation

Plugin is [available for download](https://plugins.jetbrains.com/plugin/8087) from Jetbrains repository.

1. Go to `Preferences` -> `Plugins` -> `Browser repositories...`
2. Search for `Graph Database support`.
3. Install plugin and restart IDE.

## Features

- Works in **any** Jetbrains IDE
- Manage data sources
- Write and execute queries
- Explore query results in graph or table view
- Supported databases:
  - Neo4j 3.0+ (Bolt)
- [Cypher](https://github.com/opencypher/openCypher) query language:
  - Understands queries in `.cyp`, `.cypher` or `.cql` files
  - Syntax highlight and error reporting
  - Refactoring support for identifiers, labels, relationship types and properties
  - Autocompletion support for identifiers, labels, relationship types, properties, functions and stored procedures. Information gathered from existing queries and configured data sources
  - Code reformatting
  - Provide documentation for functions and stored procedures
  - Inspections: database warnings, function checks, type system.
  - Auto-inject Cypher language for:
   [neo4j](https://github.com/neo4j/neo4j),
   [neo4j-ogm](https://github.com/neo4j/neo4j-ogm),
   [spring-data-neo4j](https://github.com/spring-projects/spring-data-neo4j),
   [neo4j-harness](https://github.com/neo4j/neo4j/tree/3.1/community/neo4j-harness),
   [py2neo](https://github.com/nigelsmall/py2neo)

## Supported Jetbrains products

* IntelliJ IDEA
* RubyMine
* WebStorm
* PhpStorm
* PyCharm
* AppCode
* Android Studio
* Datagrip
* CLion

## Development

Gradle is used as build system.

```shell
# Build plugin distribution
./gradlew buildPlugin

# Run idea in development mode
./gradlew :graph-database-support-plugin:runIde
```

## Contacts

Please report any bugs or feature request by creating [new issue on Github](https://github.com/neueda/jetbrains-plugin-graph-database-support/issues/new).

You can easily reach us in case you have any questions or just want to chat about graph databases:
- [Product Support](mailto:product.support@neueda.com) 
- Dmitry Vrublevsky ([@FylmTM](https://twitter.com/FylmTM))
- Neueda Technologies ([@NeuedaDev](https://twitter.com/NeuedaDev))

