package com.neueda.jetbrains.plugin.graphdb.test.integration.opencypher.gremlin;

import com.neueda.jetbrains.plugin.graphdb.database.opencypher.gremlin.exceptions.ExceptionWrapper;
import org.junit.Test;

import static org.junit.Assert.*;

public class ExceptionWrapperTest {
    @Test
    public void shouldEllipseString() throws Exception {
        String shortString = "DataSource[Cosmos] - metadata refresh failed. Reason: java.util.concurrent.ExecutionException:" +
                "org.apache.tinkerpop.gremlin.driver.exception.ResponseException:...";
        String longString = "DataSource[Cosmos] - metadata refresh failed. Reason: java.util.concurrent.ExecutionException:" +
                "org.apache.tinkerpop.gremlin.driver.exception.ResponseException: ActivityId : d0015df6-09d2-48a6-b3ab-32d026213a42";
        String truncatedString = ExceptionWrapper.ellipseString(longString, ExceptionWrapper.SHORT_STRING_LENGTH);
        assertEquals(shortString, truncatedString);
    }
}
