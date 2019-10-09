package com.neueda.jetbrains.plugin.graphdb.test.integration.opencypher.gremlin;

import com.neueda.jetbrains.plugin.graphdb.database.api.query.GraphQueryResult;
import com.neueda.jetbrains.plugin.graphdb.database.opencypher.gremlin.OpenCypherGremlinConfiguration;
import com.neueda.jetbrains.plugin.graphdb.database.opencypher.gremlin.OpenCypherGremlinDatabase;
import org.apache.tinkerpop.gremlin.server.GremlinServer;
import org.apache.tinkerpop.gremlin.server.Settings;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.net.URL;

import static com.neueda.jetbrains.plugin.graphdb.test.integration.opencypher.gremlin.OpenCypherGremlinDatabaseTest.*;
import static java.util.Collections.*;
import static org.assertj.core.api.Assertions.*;

public class OpenCypherGremlinDatabaseTestSecure {
    private static GremlinServer server;

    @BeforeClass
    public static void setUp() throws Exception {
        OpenCypherGremlinDatabaseTest.temp.create();
        server = setupGremlinServer();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        server.stop();
    }

    @Test
    public void wrongCredentials() throws Exception {
        OpenCypherGremlinConfiguration config = new OpenCypherGremlinConfiguration();
        config.setUser("stephen");
        config.setPassword("wrong");
        config.setHost("localhost");
        config.setPort(PORT.toString());

        OpenCypherGremlinDatabase database = new OpenCypherGremlinDatabase(config);

        assertThatThrownBy(() -> database.execute("RETURN 1"))
                    .hasMessageContaining("Database connection failed. Please check database configuration" +
                            " (including username and password) and retry to connect.");
    }

    @Test
    public void basicQuery() throws Exception {
        OpenCypherGremlinConfiguration config = new OpenCypherGremlinConfiguration();
        config.setUser("stephen");
        config.setPassword("password");
        config.setHost("localhost");
        config.setPort(PORT.toString());

        OpenCypherGremlinDatabase database = new OpenCypherGremlinDatabase(config);

        GraphQueryResult results = database.execute("MATCH (n) RETURN count(n) as count");

        assertThat(extractValues(results))
                .extracting("count")
                .containsExactly(6L);
    }

    private static GremlinServer setupGremlinServer() throws Exception {
        URL resource = OpenCypherGremlinDatabaseTestSecure.class.getResource("credentials.kryo");

        String credentialsDbPath = tempFile(
                "gremlin.graph=org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph\n" +
                        "gremlin.tinkergraph.vertexIdManager=LONG\n" +
                        "gremlin.tinkergraph.graphLocation=" + resource.getFile() + "\n" +
                        "gremlin.tinkergraph.graphFormat=gryo");

        Settings serverSettings = getServerSettings();
        serverSettings.authentication.authenticator = org.apache.tinkerpop.gremlin.server.auth.SimpleAuthenticator.class.getCanonicalName();
        serverSettings.authentication.config = singletonMap("credentialsDb", credentialsDbPath);

        GremlinServer gremlinServer = new GremlinServer(serverSettings);
        gremlinServer.start().join();

        return gremlinServer;
    }
}
