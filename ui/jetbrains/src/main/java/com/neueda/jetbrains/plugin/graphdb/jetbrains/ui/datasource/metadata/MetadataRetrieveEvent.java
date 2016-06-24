package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.metadata;

import com.intellij.util.messages.Topic;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.DataSource;

public interface MetadataRetrieveEvent {

    Topic<MetadataRetrieveEvent> METADATA_RETRIEVE_EVENT = Topic.create("GraphDatabaseDataSource.MetadataRetrieve", MetadataRetrieveEvent.class);

    void startMetadataRefresh(DataSource nodeDataSource);

    void metadataRefreshSucceed(DataSource nodeDataSource);

    void metadataRefreshFailed(DataSource nodeDataSource, Exception exception);
}
