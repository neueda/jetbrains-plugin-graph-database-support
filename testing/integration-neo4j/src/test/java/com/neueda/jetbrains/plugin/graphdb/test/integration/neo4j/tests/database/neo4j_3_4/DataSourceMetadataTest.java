package com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.tests.database.neo4j_3_4;

import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.metadata.DataSourceMetadata;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.metadata.Neo4jBoltCypherDataSourceMetadata;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.state.DataSourceApi;
import com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.data.StoredProcedure;
import com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.tests.database.common.AbstractDataSourceMetadataTest;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class DataSourceMetadataTest extends AbstractDataSourceMetadataTest {

    @Override
    public DataSourceApi getDataSource() {
        return dataSource().neo4j34();
    }

    @Override
    protected List<StoredProcedure> requiredProcedures() {
        //TODO: needs update to match actual neo 3.4 procedures
        return Arrays.asList(
                procedure("db.labels",
                        "db.labels() :: (label :: STRING?)",
                        "List all labels in the database.",
                        "READ")
        );
    }

    public void testHaveTestUserFunctions() {
        DataSourceMetadata metadata = getMetadata();
        List<Map<String, String>> userFunctionsMetadata = metadata.getMetadata(Neo4jBoltCypherDataSourceMetadata.USER_FUNCTIONS);
        assertThat(userFunctionsMetadata)
                .isNotNull()
                .hasSize(35);
    }
}
