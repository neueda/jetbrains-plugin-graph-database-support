package com.neueda.jetbrains.plugin.graphdb.language.cypher.documentation.database;

import com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.atoms.CypherBuiltInFunctions;

public abstract class CypherDocumentation {

    public static final DocumentationStorage BUILT_IN_FUNCTIONS =
            new DocumentationStorage("built-in/functions", CypherBuiltInFunctions.FUNCTION_NAMES);
}
