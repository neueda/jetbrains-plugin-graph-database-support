package org.opencypher.gremlin.neo4j.driver;

import org.apache.tinkerpop.gremlin.driver.Client;
import org.apache.tinkerpop.gremlin.driver.Cluster;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.summary.ServerInfo;
import org.opencypher.gremlin.client.CypherGremlinClient;
import org.opencypher.gremlin.neo4j.driver.GremlinServerDriver.GremlinServerInfo;

import java.util.concurrent.CompletionStage;
import java.util.function.Function;

public class GremlinServerDriverWithClient implements GremlinDriver {
    private final Cluster cluster;
    private final GremlinServerInfo serverInfo;
    private Function<Client, CypherGremlinClient> client;
    private boolean ignoreIds;

    public GremlinServerDriverWithClient(Cluster cluster, Function<Client, CypherGremlinClient> client, boolean ignoreIds) {
        this.cluster = cluster;
        this.serverInfo = new GremlinServerInfo(cluster.toString());
        this.client = client;
        this.ignoreIds = ignoreIds;
    }

    @Override
    public boolean isEncrypted() {
        return cluster.isSslEnabled();
    }

    @Override
    public Session session() {
        Client gremlinClient = cluster.connect();
        CypherGremlinClient cypherGremlinClient = client.apply(gremlinClient);
        GremlinCypherValueConverter converter = new GremlinCypherValueConverter(ignoreIds);

        return new GremlinServerSession(serverInfo, cypherGremlinClient, converter);
    }

    @Override
    public void close() {
        cluster.close();
    }

    @Override
    public CompletionStage<Void> closeAsync() {
        return cluster.closeAsync();
    }
}
