package com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.neo4j_3_0;

import java.util.Arrays;
import java.util.List;

import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.state.DataSourceApi;
import com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.common.AbstractDataSourceMetadataTest;
import com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.data.StoredProcedure;

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
}
