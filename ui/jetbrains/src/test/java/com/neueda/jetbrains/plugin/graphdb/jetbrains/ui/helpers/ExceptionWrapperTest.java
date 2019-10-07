package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.helpers;

import com.neueda.jetbrains.plugin.graphdb.jetbrains.util.ExceptionErrorMessages;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.util.ExceptionWrapper;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.opencypher.v9_0.util.SyntaxException;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

public class ExceptionWrapperTest {
    @Test
    public void shouldEllipseString() throws Exception {
        String shortString = "DataSource[Cosmos] - metadata refresh failed. Reason: java.util.concurrent.ExecutionException:org.apache.tinkerpop.gremlin.driver.exception.ResponseException:...";
        String longString = "DataSource[Cosmos] - metadata refresh failed. Reason: java.util.concurrent.ExecutionException:" +
                "org.apache.tinkerpop.gremlin.driver.exception.ResponseException: ActivityId : d0015df6-09d2-48a6-b3ab-32d026213a42";
        String truncatedString = ExceptionWrapper.ellipseString(longString, ExceptionWrapper.SHORT_STRING_LENGTH);
        assertEquals(shortString, truncatedString);
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test(expected = SyntaxException.class)
    public void shouldWrapExceptionInMeaningMessage() throws Exception {
        try {
            TestThing testThing = new TestThing();
            testThing.chuck();
            fail("should have thrown");
        } catch (SyntaxException e) {
            assertThat(ExceptionWrapper.wrapExceptionInMeaningMessage(e), is(ExceptionErrorMessages.SYNTAX_WARNING));
        }

    }

    private class TestThing {
        public void chuck() {
            String exceptionMessage = "org.opencypher.v9_0.util.SyntaxException: Invalid input 'R':...";
            throw new SyntaxException(exceptionMessage);
        }
    }
}
