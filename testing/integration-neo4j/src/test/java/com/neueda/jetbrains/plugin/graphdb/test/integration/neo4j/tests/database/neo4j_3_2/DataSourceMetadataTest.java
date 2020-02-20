//package com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.tests.database.neo4j_3_2;
//
//import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.metadata.DataSourceMetadata;
//import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.metadata.Neo4jBoltCypherDataSourceMetadata;
//import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.state.DataSourceApi;
//import com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.tests.database.common.AbstractDataSourceMetadataTest;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Map;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SuppressWarnings("unchecked")
//public class DataSourceMetadataTest extends AbstractDataSourceMetadataTest {
//
//    @Override
//    public DataSourceApi getDataSource() {
//        return dataSource().neo4j32();
//    }
//
//    @Override
//    protected List requiredProcedures() {
//        return Arrays.asList(
//                procedure("db.labels",
//                        "db.labels() :: (label :: STRING?)",
//                        "List all labels in the database."),
//                procedure("db.relationshipTypes",
//                        "db.relationshipTypes() :: (relationshipType :: STRING?)",
//                        "List all relationship types in the database."),
//                procedure("db.propertyKeys",
//                        "db.propertyKeys() :: (propertyKey :: STRING?)",
//                        "List all property keys in the database."),
//                procedure("dbms.procedures",
//                        "dbms.procedures() :: (name :: STRING?, signature :: STRING?, description :: STRING?)",
//                        "List all procedures in the DBMS."),
//                procedure("dbms.functions",
//                        "dbms.functions() :: (name :: STRING?, signature :: STRING?, description :: STRING?)",
//                        "List all user functions in the DBMS."),
//                procedure("dbms.components",
//                        "dbms.components() :: (name :: STRING?, versions :: LIST? OF STRING?, edition :: STRING?)",
//                        "List DBMS components and their versions.")
//        );
//    }
//
//    public void testHaveTestUserFunctions() {
//        DataSourceMetadata metadata = getMetadata();
//        List<Map<String, String>> userFunctionsMetadata = metadata.getMetadata(Neo4jBoltCypherDataSourceMetadata.USER_FUNCTIONS);
//        assertThat(userFunctionsMetadata)
//                .isNotNull()
//                .hasSize(2);
//    }
//}
