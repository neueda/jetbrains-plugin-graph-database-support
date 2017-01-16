package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.plan;

enum QueryPlanArgumentKeys {
    ESTIMATED_ROWS("EstimatedRows"),
    DB_HITS("DbHits"),
    ROWS("Rows"),

    PLANNER_IMPL("planner-impl"),
    RUNTIME("runtime"),
    KEY_NAMES("KeyNames"),
    RUNTIME_IMPL("runtime-impl"),
    PLANNER("planner"),
    VERSION("version");
    
    private String key;

    QueryPlanArgumentKeys(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    
}
