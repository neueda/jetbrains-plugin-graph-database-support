package com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.util.base;

import java.util.*;

import com.intellij.openapi.components.ServiceManager;
import com.intellij.testFramework.fixtures.LightCodeInsightFixtureTestCase;
import com.neueda.jetbrains.plugin.graphdb.database.neo4j.bolt.Neo4jBoltConfiguration;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.DataSourceType;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.DataSourcesComponent;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.metadata.DataSourcesComponentMetadata;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.state.DataSourceApi;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.CypherMetadataProviderService;
import com.neueda.jetbrains.plugin.graphdb.test.database.neo4j.common.Neo4jServer;
import com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.util.server.Neo4j30ServerLoader;
import com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.util.server.Neo4j31ServerLoader;

public abstract class BaseIntegrationTest extends LightCodeInsightFixtureTestCase {

    private static final String NEO4J30 = "neo4j30";
    private static final String NEO4J31 = "neo4j31";

    private Components components;
    private DataSources dataSources;
    private Services services;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        components = new Components();
        dataSources = new DataSources();
        services = new Services();

        // Clean up any data sources & their information
        List<DataSourceApi> dataSources = new ArrayList<>(component().dataSources().getDataSourceContainer().getDataSources());
        component().dataSources().getDataSourceContainer().removeDataSources(dataSources);
        services().cypherMetadataProvider().wipeAll();
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

    private DataSourceApi createDataSource(String name, Neo4jServer neo4jServer) {
        Map<String, String> configuration = new HashMap<>();
        configuration.put(Neo4jBoltConfiguration.HOST, neo4jServer.getBoltHost());
        configuration.put(Neo4jBoltConfiguration.PORT, neo4jServer.getBoltPort());
        configuration.put(Neo4jBoltConfiguration.USER, null);
        configuration.put(Neo4jBoltConfiguration.PASSWORD, null);

        return component().dataSources().getDataSourceContainer().createDataSource(
                   null,
                   DataSourceType.NEO4J_BOLT,
                   name,
                   configuration
        );
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
        private DataSourceApi neo4j30DataSource;
        private DataSourceApi neo4j31DataSource;

        public DataSourceApi neo4j30() {
            if (neo4j30DataSource == null) {
                neo4j30DataSource = component().dataSources()
                           .getDataSourceContainer()
                           .getDataSource(NEO4J30)
                           .orElseGet(() -> {
                               DataSourceApi dataSource = createDataSource(NEO4J30, Neo4j30ServerLoader.getInstance());
                               component().dataSources().getDataSourceContainer().addDataSource(dataSource);
                               component().dataSources().refreshAllMetadata();
                               return dataSource;
                           });
            }
            return neo4j30DataSource;
        }

        public DataSourceApi neo4j31() {
            if (neo4j31DataSource == null) {
                neo4j31DataSource = component().dataSources()
                           .getDataSourceContainer()
                           .getDataSource(NEO4J31)
                           .orElseGet(() -> {
                               DataSourceApi dataSource = createDataSource(NEO4J31, Neo4j31ServerLoader.getInstance());
                               component().dataSources().getDataSourceContainer().addDataSource(dataSource);
                               component().dataSources().refreshAllMetadata();
                               return dataSource;
                           });
            }
            return neo4j31DataSource;
        }
    }
}
