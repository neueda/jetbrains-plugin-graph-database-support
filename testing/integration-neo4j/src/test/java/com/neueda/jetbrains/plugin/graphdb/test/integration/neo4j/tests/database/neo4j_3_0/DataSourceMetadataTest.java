package com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.tests.database.neo4j_3_0;

import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.metadata.DataSourceMetadata;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.metadata.Neo4jBoltCypherDataSourceMetadata;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.state.DataSourceApi;
import com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.tests.database.common.AbstractDataSourceMetadataTest;
import com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.data.StoredProcedure;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class DataSourceMetadataTest extends AbstractDataSourceMetadataTest {

    @Override
    public DataSourceApi getDataSource() {
        return dataSource().neo4j30();
    }

    @Override
    protected List<StoredProcedure> requiredProcedures() {
        return Arrays.asList(
                procedure("db.labels",
                        "db.labels() :: (label :: STRING?)"),
                procedure("db.relationshipTypes",
                        "db.relationshipTypes() :: (relationshipType :: STRING?)"),
                procedure("db.propertyKeys",
                        "db.propertyKeys() :: (propertyKey :: STRING?)"),
                procedure("dbms.procedures",
                        "dbms.procedures() :: (name :: STRING?, signature :: STRING?)"),
                procedure("dbms.components",
                        "dbms.components() :: (name :: STRING?, versions :: LIST? OF STRING?, edition :: STRING?)")
        );
    }

    public void testNoUserFunctions() {
        DataSourceMetadata metadata = getMetadata();
        List<Map<String, String>> userFunctionsMetadata = metadata.getMetadata(Neo4jBoltCypherDataSourceMetadata.USER_FUNCTIONS);
        assertThat(userFunctionsMetadata).isEmpty();
    }
}
