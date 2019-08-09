package com.neueda.jetbrains.plugin.graphdb.platform;

public final class GraphConstants {

    public static final boolean IS_DEVELOPMENT = System.getProperty("graphDatabaseSupportDevelopment") != null;

    public static final String BOUND_DATA_SOURCE_PREFIX = "graphdbBoundDataSource-";
    public static final String PLUGIN_ID = "com.neueda4j.intellij.plugin.cypher";

    public static class ToolWindow {
        public static final String CONSOLE_TOOL_WINDOW = "Graph Database Console";

        public static class Tabs {
            public static final String LOG = "Log";
            public static final String GRAPH = "Graph";
            public static final String TABLE = "Table";
            public static final String PARAMETERS = "Parameters";
        }
    }

    public static class Actions {
        public static final String CONSOLE_ACTIONS = "GraphDatabaseConsoleToolWindowActions";
    }

    private GraphConstants() {
    }
}
