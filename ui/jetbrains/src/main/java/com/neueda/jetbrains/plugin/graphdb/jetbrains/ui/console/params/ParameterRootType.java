package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.params;

import com.intellij.execution.console.ConsoleRootType;
import org.jetbrains.annotations.NotNull;

public class ParameterRootType extends ConsoleRootType {

    @NotNull
    public static ParameterRootType getInstance() {
        return findByClass(ParameterRootType.class);
    }

    ParameterRootType() {
        super("graphdb-parameters", "Graph Database parameters");
    }
}
