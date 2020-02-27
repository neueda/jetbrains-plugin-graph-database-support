package com.neueda.jetbrains.plugin.graphdb.test.database.neo4j_4_0;

import org.neo4j.procedure.Name;
import org.neo4j.procedure.UserFunction;

public class Neo4jTestUserFunction {

    @UserFunction
    public String firstTestFunction() {
        return "test";
    }

    @UserFunction
    public String secondTestFunction(@Name("param") String param) {
        return "test";
    }
}
