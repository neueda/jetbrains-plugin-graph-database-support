package com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.tests.database.common;

import static org.assertj.core.api.Assertions.*;

import java.util.*;
import java.util.stream.Collectors;

import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.metadata.DataSourceMetadata;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.metadata.Neo4jBoltCypherDataSourceMetadata;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.state.DataSourceApi;
import com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.data.StoredProcedure;
import com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.util.base.BaseIntegrationTest;

@SuppressWarnings("unchecked")
public abstract class AbstractDataSourceMetadataTest extends BaseIntegrationTest {

    @Override
    public void setUp() throws Exception {
        super.setUp();
        component().dataSources().refreshAllMetadata();
    }

    public abstract DataSourceApi getDataSource();

    public void testMetadataExists() throws Exception {
        Optional<DataSourceMetadata> metadata = component().dataSourcesMetadata().getMetadata(getDataSource());
        assertThat(metadata).isPresent();
    }

    public void testMetadataHaveRequiredProcedures() {
        DataSourceMetadata metadata = getMetadata();
        List<Map<String, String>> storedProcedures = metadata.getMetadata(Neo4jBoltCypherDataSourceMetadata.STORED_PROCEDURES);

        List<Map<String, String>> requiredProcedures = requiredProcedures().stream()
                   .map(StoredProcedure::asMap)
                   .collect(Collectors.toList());
        assertThat(storedProcedures)
                   .containsAll(requiredProcedures);
    }

    protected abstract List<StoredProcedure> requiredProcedures();

    protected StoredProcedure procedure(final String name, final String signature) {
        return new StoredProcedure(name, signature);
    }

    protected StoredProcedure procedure(final String name, final String signature, final String description) {
        return new StoredProcedure(name, signature, description);
    }

    protected DataSourceMetadata getMetadata() {
        return component()
                   .dataSourcesMetadata()
                   .getMetadata(getDataSource())
                   .orElseThrow(() -> new IllegalStateException("Metadata should not be null"));
    }
}
