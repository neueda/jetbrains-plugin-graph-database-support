package com.neueda.jetbrains.plugin.graphdb.database.api.data;

import java.util.List;

public interface GraphEntity extends NoIdGraphEntity {

    String getId();

    GraphPropertyContainer getPropertyContainer();

    List<String> getTypes();

    String getTypesName();

}
