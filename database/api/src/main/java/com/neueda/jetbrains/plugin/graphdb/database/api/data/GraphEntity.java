package com.neueda.jetbrains.plugin.graphdb.database.api.data;

import java.util.List;

public interface GraphEntity {

    String getId();

    GraphPropertyContainer getPropertyContainer();

    List<String> getTypes();

    String getRepresentation();
}
