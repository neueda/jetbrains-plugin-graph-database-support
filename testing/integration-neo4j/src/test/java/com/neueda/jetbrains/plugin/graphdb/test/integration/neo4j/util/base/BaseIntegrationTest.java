package com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.util.base;

import java.util.HashMap;
import java.util.Map;

import com.intellij.testFramework.fixtures.LightCodeInsightFixtureTestCase;
import com.neueda.jetbrains.plugin.graphdb.database.neo4j.bolt.Neo4jBoltConfiguration;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.DataSourceType;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.DataSourcesComponent;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.metadata.DataSourcesComponentMetadata;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.state.DataSourceApi;
import com.neueda.jetbrains.plugin.graphdb.test.database.neo4j.common.Neo4jServer;
import com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.util.server.Neo4j30ServerLoader;
import com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.util.server.Neo4j31ServerLoader;

public abstract class BaseIntegrationTest extends LightCodeInsightFixtureTestCase {

    private static final String NEO4J30 = "neo4j30";
    private static final String NEO4J31 = "neo4j31";

    private Components components;
    private DataSources dataSources;
    private DataSourceApi neo4j30DataSource;
    private DataSourceApi neo4j31DataSource;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        components = new Components();
        dataSources = new DataSources();

        neo4j30DataSource = component().dataSources()
                   .getDataSourceContainer()
                   .getDataSource(NEO4J30)
                   .orElseGet(() -> {
                       DataSourceApi dataSource = createDataSource(NEO4J30, Neo4j30ServerLoader.getInstance());
                       component().dataSources().getDataSourceContainer().addDataSource(dataSource);
                       return dataSource;
                   });
        neo4j31DataSource = component().dataSources()
                   .getDataSourceContainer()
                   .getDataSource(NEO4J31)
                   .orElseGet(() -> {
                       DataSourceApi dataSource = createDataSource(NEO4J31, Neo4j31ServerLoader.getInstance());
                       component().dataSources().getDataSourceContainer().addDataSource(dataSource);
                       return dataSource;
                   });
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

    public final class Components {
        public DataSourcesComponent dataSources() {
            return getProject().getComponent(DataSourcesComponent.class);
        }

        public DataSourcesComponentMetadata dataSourcesMetadata() {
            return getProject().getComponent(DataSourcesComponentMetadata.class);
        }
    }

    public final class DataSources {
        public DataSourceApi neo4j30() {
            return neo4j30DataSource;
        }

        public DataSourceApi neo4j31() {
            return neo4j31DataSource;
        }
    }
}
