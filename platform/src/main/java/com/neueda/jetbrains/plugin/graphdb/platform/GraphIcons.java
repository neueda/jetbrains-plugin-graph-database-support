package com.neueda.jetbrains.plugin.graphdb.platform;

import javax.swing.Icon;

import static com.intellij.openapi.util.IconLoader.getIcon;

public final class GraphIcons {
    public static final class Database {
        public static final Icon UNKNOWN = getIcon("/graphdb/icons/database/unknown_16x16.png");
        public static final Icon NEO4J = getIcon("/graphdb/icons/database/neo4j_16x16.png");
    }

    public static final class Language {
        public static final Icon CYPHER = getIcon("/graphdb/icons/language/cypher_16x16.png");
    }

    public static final class Window {
        public static final Icon DATABASE = getIcon("/graphdb/icons/window/database_12x16.png");
        public static final Icon GRAPH = getIcon("/graphdb/icons/window/graph_13x13.png");
    }

    private GraphIcons() {
    }
}
