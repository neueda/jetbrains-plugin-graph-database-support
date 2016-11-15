package com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.atoms;

import com.google.common.collect.Lists;

import java.util.List;

public class CypherBuiltInProcedures {

    public static final List<String> PROCEDURE_NAMES = Lists.newArrayList(
            "db.labels",
            "db.relationshipTypes",
            "db.propertyKeys",
            "db.indexes",
            "db.constraints",
            "dbms.procedures",
            "dbms.components",
            "dbms.queryJmx",
            "dbms.changePassword"
    );
}
