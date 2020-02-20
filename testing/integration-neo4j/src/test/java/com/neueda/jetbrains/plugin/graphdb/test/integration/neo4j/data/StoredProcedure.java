package com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.data;

import java.util.HashMap;
import java.util.Map;

public class StoredProcedure {
    enum Style {nullsIncluded, nullsHidden}

    private Style style;
    private final String name;
    private final String signature;
    private final String description;
    private final String mode;
    private final String worksOnSystem;
    private final String defaultBuiltInRoles;

//    public StoredProcedure(String name, String signature) {
//        this(name, signature, null);
//    }

//    public StoredProcedure(String name, String signature, String description) {
//        this(name, signature, description, null);
//}

    public StoredProcedure(String name, String signature, String description, String mode) {
        this(name, signature, description, mode, null);
        style = Style.nullsHidden;
    }

    public StoredProcedure(String name, String signature, String description, String mode, String worksOnSystem) {
        this(name, signature, description, mode, worksOnSystem, null);
    }

    public StoredProcedure(String name, String signature, String description, String mode, String worksOnSystem, String defaultBuiltInRoles) {
        this.name = name;
        this.signature = signature;
        this.description = description;
        this.mode = mode;
        this.worksOnSystem = worksOnSystem;
        this.defaultBuiltInRoles = defaultBuiltInRoles;
        this.style = Style.nullsIncluded;
    }

    public Map<String, String> asMap() {
        Map<String, String> procedure = new HashMap<>();
        procedure.put("name", name);
        procedure.put("signature", signature);
        switch (style) {
            case nullsHidden:
                if (description != null) {
                    procedure.put("description", description);
                }
                if (mode != null) {
                    procedure.put("mode", mode);
                }
                break;
            case nullsIncluded:
                procedure.put("description", description);
                procedure.put("mode", mode);
                procedure.put("worksOnSystem", worksOnSystem);
                procedure.put("defaultBuiltInRoles", defaultBuiltInRoles);
        }
        return procedure;
    }
}
