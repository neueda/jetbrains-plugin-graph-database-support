package com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.metadata;

import java.util.List;
import java.util.Map;

public interface DataSourceMetadata {

    List<Map<String, String>> getMetadata(String metadataKey);
}
