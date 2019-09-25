package com.neueda.jetbrains.plugin.graphdb.database.opencypher.gremlin;

import org.apache.tinkerpop.gremlin.driver.Client;
import org.opencypher.gremlin.client.CypherGremlinClient;
import org.opencypher.gremlin.translation.translator.Translator;

import java.util.function.Function;

import static org.opencypher.gremlin.client.CypherGremlinClient.*;
import static org.opencypher.gremlin.translation.translator.TranslatorFlavor.*;

public enum GremlinFlavor {
    GREMLIN("Gremlin (Default)", CypherGremlinClient::translating),
    GREMLIN33x("Gremlin (for TinkerPop<=3.3)", c -> translating(c, gremlinServer33x())),
    COSMOSDB("Cosmos DB", c -> retrieving(c, cosmosDb())),
    NEPTUNE("AWS Neptune", c -> translating(c,
            () -> Translator.builder()
                    .gremlinGroovy()
                    .inlineParameters()
                    .enableMultipleLabels()
                    .build(neptune()))),
    PLUGIN("Cypher for Gremlin plugin", CypherGremlinClient::plugin);

    final String displayName;
    final Function<Client, CypherGremlinClient> wrapClient;

    GremlinFlavor(String displayName, Function<Client, CypherGremlinClient> client) {
        this.displayName = displayName;
        this.wrapClient = client;
    }

    @Override
    public String toString() {
        return displayName;
    }

    public CypherGremlinClient client(Client gremlinClient) {
        return wrapClient.apply(gremlinClient);
    }
}
