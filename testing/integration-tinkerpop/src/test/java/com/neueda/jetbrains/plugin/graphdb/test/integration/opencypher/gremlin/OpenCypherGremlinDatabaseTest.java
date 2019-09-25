package com.neueda.jetbrains.plugin.graphdb.test.integration.opencypher.gremlin;

import com.neueda.jetbrains.plugin.graphdb.database.api.data.GraphEntity;
import com.neueda.jetbrains.plugin.graphdb.database.api.data.GraphMetadata;
import com.neueda.jetbrains.plugin.graphdb.database.api.data.GraphNode;
import com.neueda.jetbrains.plugin.graphdb.database.api.query.GraphQueryResult;
import com.neueda.jetbrains.plugin.graphdb.database.api.query.GraphQueryResultColumn;
import com.neueda.jetbrains.plugin.graphdb.database.opencypher.gremlin.OpenCypherGremlinConfiguration;
import com.neueda.jetbrains.plugin.graphdb.database.opencypher.gremlin.OpenCypherGremlinDatabase;
import org.apache.tinkerpop.gremlin.server.GremlinServer;
import org.apache.tinkerpop.gremlin.server.Settings;
import org.assertj.core.api.iterable.ThrowingExtractor;
import org.assertj.core.groups.Tuple;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.*;
import static java.util.Collections.*;
import static java.util.stream.Collectors.*;
import static org.assertj.core.api.Assertions.*;

public class OpenCypherGremlinDatabaseTest {
    final static Integer PORT = 8183;

    private final static Tuple PETER = tuple(true, "6", singletonList("person"), "peter");
    private final static Tuple LOP = tuple(true, "3", singletonList("software"), "lop");
    private final static Tuple PETER_CREATED_LOP = tuple(false, "12", singletonList("created"), null);

    private static GremlinServer server;
    private static OpenCypherGremlinDatabase database;

    @ClassRule
    public static TemporaryFolder temp = new TemporaryFolder();

    @BeforeClass
    public static void setUp() throws Exception {
        server = setupGremlinServer();
        database = setupDatabase();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        server.stop();
    }

    @Test
    public void basicQuery() throws Exception {
        GraphQueryResult results = database.execute("MATCH (n) RETURN count(n) as count");

        assertThat(extractValues(results))
                .extracting("count")
                .containsExactly(6L);
    }

    @Test
    public void parameterQuery() throws Exception {
        GraphQueryResult results = database.execute("MATCH (n {name: $name}) RETURN n.age", singletonMap("name", "marko"));

        assertThat(extractValues(results))
                .extracting("n.age")
                .containsExactly(29L);
    }

    @Test
    public void queryMeta() throws Exception {
        GraphQueryResult results = database.execute("MATCH (n) RETURN n.lang, count(n) as count");

        assertThat(results.getColumns())
                .extracting(GraphQueryResultColumn::getName)
                .containsExactly("n.lang", "count");

        assertThat(results.getNodes()).isEmpty();
        assertThat(results.getRelationships()).isEmpty();

        assertThat(results.getNotifications()).isEmpty();
        assertThat(results.getResultSummary()).contains("Execution time");
        assertThat(results.getExecutionTimeMs()).isGreaterThan(0);

        assertThat(results.hasPlan()).isFalse();
        assertThat(results.isProfilePlan()).isFalse();
        assertThat(results.getPlan()).isEmpty();

        assertThat(extractValues(results))
                .extracting("n.lang", "count")
                .containsExactly(
                        tuple("java", 2L),
                        tuple(null, 4L));
    }

    @Test
    public void queryElementsMeta() throws Exception {
        GraphQueryResult results = database.execute("MATCH (n {name: 'peter'})-[r]->(m) RETURN n, r, m");

        assertThat(results.getColumns())
                .extracting(GraphQueryResultColumn::getName)
                .containsExactly("n", "r", "m");

        assertThat(results.getNodes()).flatExtracting(GraphEntity::getTypes).containsExactlyInAnyOrder("person", "software");
        assertThat(results.getRelationships()).flatExtracting(GraphEntity::getTypes).containsExactly("created");

        assertThat(results.getNotifications()).isEmpty();
        assertThat(results.getResultSummary()).contains("Execution time");
        assertThat(results.getExecutionTimeMs()).isGreaterThan(0);

        assertThat(results.hasPlan()).isFalse();
        assertThat(results.isProfilePlan()).isFalse();
        assertThat(results.getPlan()).isEmpty();

        assertThat(extractValues(results)).hasSize(1);
    }


    @Test
    public void metadata() {
        GraphMetadata metadata = database.metadata();

        assertThat(metadata.labels()).containsExactly(
                entry("software", 2L),
                entry("person", 4L)
        );

        assertThat(metadata.relationships()).containsExactly(
                entry("created", 4L),
                entry("knows", 2L)
        );

        assertThat(metadata.edgeProperties()).containsExactlyInAnyOrder("weight");

        assertThat(metadata.vertexProperties()).containsExactlyInAnyOrder("name", "age", "lang");
    }

    @Test
    public void returnPath() throws Exception {
        GraphQueryResult results = database.execute("MATCH p=(n {name: 'peter'})-[r]->(m) RETURN p");

        assertThat(results.getNodes())
                .extracting(extractElement())
                .containsExactlyInAnyOrder(
                        PETER,
                        LOP
                );

        assertThat(results.getRelationships())
                .extracting(extractElement())
                .containsExactlyInAnyOrder(
                        PETER_CREATED_LOP
                );

        assertThat(extractValues(results))
                .extracting("p")
                .flatExtracting("components")
                .extracting(extractElement())
                .containsExactly(PETER, PETER_CREATED_LOP, LOP);
    }

    @Test
    public void returnListElements() throws Exception {
        GraphQueryResult results = database.execute("MATCH p=(n {name: 'peter'})-[r]->(m) RETURN " +
                "nodes(p) as myNodes," +
                "relationships(p) as myRels");

        assertThat(results.getNodes())
                .extracting(extractElement())
                .containsExactlyInAnyOrder(
                        PETER,
                        LOP
                );

        assertThat(results.getRelationships())
                .extracting(extractElement())
                .containsExactlyInAnyOrder(
                        PETER_CREATED_LOP
                );

        assertThat(extractValues(results))
                .flatExtracting("myNodes")
                .extracting(extractElement())
                .containsExactlyInAnyOrder(PETER, LOP);

        assertThat(extractValues(results))
                .flatExtracting("myRels")
                .extracting(extractElement())
                .containsExactly(PETER_CREATED_LOP);
    }

    @Test
    public void returnList() throws Exception {
        GraphQueryResult results = database.execute("RETURN [1, 2, 3] as list");

        assertThat(results.getNodes()).isEmpty();
        assertThat(results.getRelationships()).isEmpty();

        assertThat(extractValues(results))
                .extracting("list")
                .containsExactly(asList(1L, 2L, 3L));
    }

    @Test
    public void returnMap() throws Exception {
        GraphQueryResult results = database.execute("RETURN { key: 'Value', listKey: [{ inner: 'Map1' }, { inner: 'Map2' }]} as nap");

        assertThat(results.getNodes()).isEmpty();
        assertThat(results.getRelationships()).isEmpty();

        HashMap<Object, Object> expected = new HashMap<>();
        expected.put("key", "Value");
        expected.put("listKey", Arrays.asList(
                singletonMap("inner", "Map1"),
                singletonMap("inner", "Map2")
        ));

        assertThat(extractValues(results))
                .extracting("nap")
                .containsExactly(expected);
    }

    private ThrowingExtractor<Object, Tuple, RuntimeException> extractElement() {
        return o -> {
            if (!(o instanceof GraphEntity)) {
                throw new RuntimeException("Expected GraphEntity got: "+o);
            }
            GraphEntity e = (GraphEntity) o;
            return tuple(e instanceof GraphNode, e.getId(), e.getTypes(), e.getPropertyContainer().getProperties().get("name"));
        };
    }


    private static GremlinServer setupGremlinServer() throws Exception {
        Settings serverSettings = getServerSettings();
        GremlinServer gremlinServer = new GremlinServer(serverSettings);
        gremlinServer.start().join();

        return gremlinServer;
    }

    static Settings getServerSettings() throws Exception {
        String graphPath = tempFile(
                "gremlin.graph=org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph\n" +
                        "gremlin.tinkergraph.vertexIdManager=ANY");

        String scriptPath = tempFile(
                "def globals = [:]\n" +
                        "globals << [hook: [ onStartUp: { ctx -> org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerFactory.generateModern(graph) }] as LifeCycleHook]\n" +
                        "globals << [g: graph.traversal()]");

        Settings serverSettings = new Settings();
        serverSettings.port = PORT;
        serverSettings.graphs = singletonMap("graph", graphPath);
        Settings.ScriptEngineSettings gremlinGroovy = serverSettings.scriptEngines.get("gremlin-groovy");
        gremlinGroovy.imports.add("java.lang.Math");
        gremlinGroovy.plugins.put("org.apache.tinkerpop.gremlin.server.jsr223.GremlinServerGremlinPlugin", emptyMap());
        gremlinGroovy.plugins.put("org.apache.tinkerpop.gremlin.tinkergraph.jsr223.TinkerGraphGremlinPlugin", emptyMap());
        gremlinGroovy.plugins.put("org.apache.tinkerpop.gremlin.jsr223.ScriptFileGremlinPlugin", singletonMap("files", singletonList(scriptPath)));
        gremlinGroovy.staticImports.add("java.lang.Math.PI");

        return serverSettings;
    }

    private static OpenCypherGremlinDatabase setupDatabase() {
        OpenCypherGremlinConfiguration config = new OpenCypherGremlinConfiguration();
        config.setHost("localhost");
        config.setPort(PORT.toString());

        return new OpenCypherGremlinDatabase(config);
    }


    static List<Map<String, Object>> extractValues(GraphQueryResult results) {
        List<GraphQueryResultColumn> columns = results.getColumns();

        return results.getRows().stream()
                .map(r -> columns.stream()
                        .collect(HashMap<String, Object>::new, (m, v) -> m.put(v.getName(), r.getValue(v)), HashMap::putAll)
                ).collect(toList());
    }


    static String tempFile(String content) throws Exception {
        File file = temp.newFile();
        Files.write(file.toPath(), content.getBytes(), StandardOpenOption.CREATE);
        return file.getAbsolutePath();
    }
}
