package com.neueda.jetbrains.plugin.graphdb.database.api.data;

public interface GraphEntity {

    String getId();

    GraphPropertyContainer getPropertyContainer();

    String getRepresentation();
}
