package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.interactions;

import com.intellij.execution.console.ConsoleRootType;
import org.jetbrains.annotations.NotNull;

public class GraphDbConsoleRootType extends ConsoleRootType {

    @NotNull
    public static GraphDbConsoleRootType getInstance() {
        return findByClass(GraphDbConsoleRootType.class);
    }

    GraphDbConsoleRootType() {
        super("graphdb-editors", "Graph Database Data Source editor");
    }
}
