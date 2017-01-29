# JetBrains IDE plugin - Graph Database support 

## About the author

* Name: Dmitry Vrublevsky
* Title: Lead Software Engineer
* Company: Neueda

> TODO: What Information I need here?

## Introduction

The story of a plugin starts a bit more than a year ago.

I have started to work with Neo4j daily and writing Cypher queries started to become a routine.

What tools I had to help myself? Not much:
* Neo4j Browser
* [Cypher for VIM](https://github.com/neo4j-contrib/cypher-vim-syntax)
* [Cypher for Sublime](https://github.com/kollhof/sublime-cypher)

While Neo4j Browser is quite awesome and helps a *lot* I found myself struggling with it.

Being Java developer for quite some time, I started to become lazy.
By being "lazy" I mean that I want my tools to help me write code.

As someone might guess, I am fan of IntelliJ IDEA.
It help write Java code effectively by being smart ... blah blah ...

## Overview

First of all, plugin works in **any** IDE by JetBrains.
It doesn't matter are you Java, PHP, Ruby, Golang or <language of your choice> developer -
Neo4j can be home for everyone (TODO: Joke is somewhere there).

In the box can be found:
* Cypher support
    * Autocompletion
    * Refactoring
    * Quick documentation
    * Auto-injection for popular libraries
    * Inspections
* Ability to manage data sources
    * Neo4j 3.0 and higher is supported
* Execute queries
    * View results as a graph, or table
    * Query parameters are supported too
    * `EXPLAIN` and `PROFILE` are supported as well

## Features

Let's explore some of the most important features in detail.

### Data sources

Data sources is a heart of a plugin.

Create data source that connects to running Neo4j instance.
After doing that you will see metadata that is pulled out from a database.

Now you can double-click on data source (or right-click and select appropriate menu option).
Now you can write queries with all the features that plugin offers:
* Metadata autocompletion
* Database-based inspections (warning)

### Query editor

> What I am going to talk about there?

* Green border
* <ctrl>-<enter> to run query

### Cypher

* Autocompletion
* Refactoring
* Documentation
* Parameters
* Inspection - warning

### Console

#### Log

> Maybe I don't need to talk about Log at all?

Log contains information about all executed queries and their stats (time, counters).
Also all errors can be found there

#### Graph

While not best graph visualisation in the world, it still works and delivers.

Features:
* Click on node 

#### Table

Table is quite simple and boring.
Nothing much to say.

## Stats

> Do I need this?
> It shows that we are spying :(

Total downloads: ~14.000
Active daily users: 400

## In the end

Any feedback is appreciated
