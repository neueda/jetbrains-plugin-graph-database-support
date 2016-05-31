package lv.neueda.jetbrains.plugin.graphdb.database.api;

import java.util.Map;

public interface GraphEntity {

    String getId();

    Map<String, Object> getProperties();
}
