package com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.util.base;

import com.intellij.openapi.components.ServiceManager;
import com.intellij.testFramework.fixtures.LightCodeInsightFixtureTestCase;
import com.neueda.jetbrains.plugin.graphdb.database.neo4j.bolt.Neo4jBoltConfiguration;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.DataSourceType;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.DataSourcesComponent;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.metadata.DataSourcesComponentMetadata;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.state.DataSourceApi;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.CypherMetadataContainer;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.CypherMetadataProviderService;
import com.neueda.jetbrains.plugin.graphdb.test.database.neo4j.common.Neo4jServer;
import com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.util.server.Neo4j34ServerLoader;
import com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.util.server.Neo4j35ServerLoader;
import com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.util.server.Neo4j40ServerLoader;
import com.neueda.jetbrains.plugin.graphdb.test.mocks.services.DummyExecutorService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseIntegrationTest extends LightCodeInsightFixtureTestCase {

    private static final String NEO4J34 = "neo4j34";
    private static final String NEO4J35 = "neo4j35";
    private static final String NEO4J40 = "neo4j40";
    private static final String UNAVAILABLE_DS = "unavailable";
    protected CypherMetadataContainer metadata;
    private Components components;
    private DataSources dataSources;
    private Services services;

    @Override
    public void setUp() throws Exception {
        DummyExecutorService.register();
        super.setUp();

        components = new Components();
        dataSources = new DataSources();
        services = new Services();

        // Clean up any data sources & their information
        List<DataSourceApi> dataSources = new ArrayList<>(component().dataSources().getDataSourceContainer().getDataSources());
        component().dataSources().getDataSourceContainer().removeDataSources(dataSources);
        services().cypherMetadataProvider().wipeAll();

        metadata = services().cypherMetadataProvider().getContainer("documentationTest");
    }

    public Services services() {
        return services;
    }

    public Components component() {
        return components;
    }

    public DataSources dataSource() {
        return dataSources;
    }

    private DataSourceApi createDataSource(String name, String host, String port, String user, String password) {
        Map<String, String> configuration = new HashMap<>();
        configuration.put(Neo4jBoltConfiguration.HOST, host);
        configuration.put(Neo4jBoltConfiguration.PORT, port);
        configuration.put(Neo4jBoltConfiguration.USER, user);
        configuration.put(Neo4jBoltConfiguration.PASSWORD, password);

        return component().dataSources().getDataSourceContainer().createDataSource(
                null,
                DataSourceType.NEO4J_BOLT,
                name,
                configuration
        );
    }

    private DataSourceApi createDataSource(String name, Neo4jServer neo4jServer) {
        return createDataSource(name, neo4jServer.getBoltHost(), neo4jServer.getBoltPort(), null, null);
    }

    private DataSourceApi getNeo4jDataSource(String dataSourceName, Neo4jServer server) {
        return component().dataSources()
                .getDataSourceContainer()
                .getDataSource(dataSourceName)
                .orElseGet(() -> {
                    DataSourceApi dataSource = createDataSource(dataSourceName, server);
                    component().dataSources().getDataSourceContainer().addDataSource(dataSource);
                    component().dataSources().refreshAllMetadata();
                    return dataSource;
                });
    }

    public final class Services {
        public CypherMetadataProviderService cypherMetadataProvider() {
            return ServiceManager.getService(getProject(), CypherMetadataProviderService.class);
        }
    }

    public final class Components {
        public DataSourcesComponent dataSources() {
            return getProject().getComponent(DataSourcesComponent.class);
        }

        public DataSourcesComponentMetadata dataSourcesMetadata() {
            return getProject().getComponent(DataSourcesComponentMetadata.class);
        }
    }

    /**
     * Lazily bootstrap Neo4j server and create data sources.
     * Some tests might even don't need running Neo4j server!
     */
    public final class DataSources {
        private DataSourceApi neo4j34DataSource;
        private DataSourceApi neo4j35DataSource;
        private DataSourceApi neo4j40DataSource;
        private DataSourceApi unavailableDataSource;

        public DataSourceApi neo4j34() {
            if (neo4j34DataSource == null) {
                neo4j34DataSource = getNeo4jDataSource(NEO4J34, Neo4j34ServerLoader.getInstance());
            }
            return neo4j34DataSource;
        }

        public DataSourceApi neo4j35() {
            if (neo4j35DataSource == null) {
                neo4j35DataSource = getNeo4jDataSource(NEO4J35, Neo4j35ServerLoader.getInstance());
            }
            return neo4j35DataSource;
        }

        public DataSourceApi neo4j40() {
            if (neo4j40DataSource == null) {
                neo4j40DataSource = getNeo4jDataSource(NEO4J40, Neo4j40ServerLoader.getInstance());
            }
            return neo4j40DataSource;
        }

        public DataSourceApi unavailable() {
            if (unavailableDataSource == null) {
                unavailableDataSource = component().dataSources()
                        .getDataSourceContainer()
                        .getDataSource(UNAVAILABLE_DS)
                        .orElseGet(() -> {
                            DataSourceApi dataSource = createDataSource(UNAVAILABLE_DS, "unexisting.domain.dev",
                                    "7474", null, null);
                            component().dataSources().getDataSourceContainer().addDataSource(dataSource);
                            component().dataSources().refreshAllMetadata();
                            return dataSource;
                        });
            }
            return unavailableDataSource;
        }
    }
}
