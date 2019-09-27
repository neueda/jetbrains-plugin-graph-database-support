package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.metadata;

import com.intellij.util.messages.MessageBus;
import com.neueda.jetbrains.plugin.graphdb.database.api.GraphDatabaseApi;
import com.neueda.jetbrains.plugin.graphdb.database.api.data.GraphMetadata;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.DataSourceType;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.metadata.DataSourceMetadata;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.metadata.DataSourcesComponentMetadata;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.metadata.Neo4jBoltCypherDataSourceMetadata;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.state.impl.DataSourceV1;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.database.DatabaseManagerService;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.CypherMetadataContainer;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.CypherMetadataProviderService;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.elements.CypherLabelElement;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.elements.CypherPropertyKeyElement;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.elements.CypherRelationshipTypeElement;
import com.neueda.jetbrains.plugin.graphdb.test.mocks.services.DummyExecutorService;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import static com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.metadata.Neo4jBoltCypherDataSourceMetadata.PROPERTY_KEYS;
import static java.util.Arrays.asList;
import static java.util.Collections.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GremlinMetadataTest {
    @Test
    @SuppressWarnings("unchecked")
    public void getMetadata() throws ExecutionException, InterruptedException {

        DataSourceV1 dataSource = new DataSourceV1(UUID.randomUUID().toString(), "test", DataSourceType.OPENCYPHER_GREMLIN, emptyMap());
        CypherMetadataContainer container = new CypherMetadataContainer();

        MessageBus messageBusMock = mock(MessageBus.class);
        when(messageBusMock.syncPublisher(MetadataRetrieveEvent.METADATA_RETRIEVE_EVENT)).thenReturn(mock(MetadataRetrieveEvent.class));

        GraphDatabaseApi databaseMock = mock(GraphDatabaseApi.class);
        when(databaseMock.metadata()).thenAnswer(e -> metadata());

        DatabaseManagerService databaseManagerMock = mock(DatabaseManagerService.class);
        when(databaseManagerMock.getDatabaseFor(dataSource)).thenReturn(databaseMock);

        CypherMetadataProviderService containerMock = mock(CypherMetadataProviderService.class);
        when(containerMock.getContainer(dataSource.getName())).thenReturn(container);

        DataSourcesComponentMetadata componentMetadata =
                new DataSourcesComponentMetadata(
                        messageBusMock,
                        databaseManagerMock,
                        containerMock,
                        new DummyExecutorService()
                );

        Optional<DataSourceMetadata> result = componentMetadata.getMetadata(dataSource).get();

        assertThat(result).isPresent();
        assertThat(result.get().getMetadata(PROPERTY_KEYS))
                .flatExtracting(Map::entrySet)
                .containsExactlyInAnyOrder(
                        entry("propertyKey", "vp1"),
                        entry("propertyKey", "vp2"),
                        entry("propertyKey", "vp3"),
                        entry("propertyKey", "ep1"),
                        entry("propertyKey", "ep2"),
                        entry("propertyKey", "ep3")
                );

        assertThat(((Neo4jBoltCypherDataSourceMetadata) result.get()).getLabels())
                .extracting(e -> entry(e.getName(), e.getCount()))
                .containsExactly(entry("TheLabel", 10L));

        assertThat(((Neo4jBoltCypherDataSourceMetadata) result.get()).getRelationshipTypes())
                .extracting(e -> entry(e.getName(), e.getCount()))
                .containsExactly(entry("knows", 20L));

        assertThat(container.getPropertyKeys())
                .extracting(CypherPropertyKeyElement::getName)
                .containsExactlyInAnyOrder("vp1", "vp2", "vp3", "ep1", "ep2", "ep3");

        assertThat(container.getLabels())
                .extracting(CypherLabelElement::getName)
                .containsExactlyInAnyOrder("TheLabel");

        assertThat(container.getRelationshipTypes())
                .extracting(CypherRelationshipTypeElement::getName)
                .containsExactlyInAnyOrder("knows");
    }

    @NotNull
    private GraphMetadata metadata() {
        return new GraphMetadata() {
            @Override
            public Map<String, Number> labels() {
                return singletonMap("TheLabel", 10L);
            }

            @Override
            public Map<String, Number> relationships() {
                return singletonMap("knows", 20L);
            }

            @Override
            public Iterable<String> vertexProperties() {
                return asList("vp1", "vp2", "vp3");
            }

            @Override
            public Iterable<String> edgeProperties() {
                return asList("ep1", "ep2", "ep3");
            }
        };
    }
}
