package com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.tests.jetbrains;

import com.intellij.psi.PsiFile;
import com.intellij.util.messages.MessageBus;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.actions.execute.StatementCollector;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.event.QueryParametersRetrievalErrorEvent;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.params.ParametersProvider;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.params.ParametersService;
import com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.tests.cypher.util.BaseGenericTest;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StatementCollectorTest extends BaseGenericTest {

    private static final String EMPTY_PARAMETERS = "{}";
    private static final String VALID_PARAMETERS = "{\"name\": \"Andrew\"}";
    private static final String WRONG_PARAMETERS = "{wrong json}";
    private StatementCollector statementCollector;
    private ParametersService parametersService;
    private ParametersProvider emptyParametersProvider, validParametersProvider, wrongParametersProvider;
    private MessageBus messageBusMock;
    private QueryParametersRetrievalErrorEvent eventMock;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        messageBusMock = Mockito.mock(MessageBus.class);
        eventMock = Mockito.mock(QueryParametersRetrievalErrorEvent.class);
        when(messageBusMock.syncPublisher(any())).thenReturn(eventMock);

        parametersService = new ParametersService();
        emptyParametersProvider = new ParametersProvider() {
            @Override
            public String getGlobalParametersJson() {
                return EMPTY_PARAMETERS;
            }

            @Override
            public String getFileSpecificParametersJson() {
                return EMPTY_PARAMETERS;
            }
        };
        validParametersProvider = new ParametersProvider() {
            @Override
            public String getGlobalParametersJson() {
                return VALID_PARAMETERS;
            }

            @Override
            public String getFileSpecificParametersJson() {
                return VALID_PARAMETERS;
            }
        };
        wrongParametersProvider = new ParametersProvider() {
            @Override
            public String getGlobalParametersJson() {
                return WRONG_PARAMETERS;
            }

            @Override
            public String getFileSpecificParametersJson() {
                return WRONG_PARAMETERS;
            }
        };
        statementCollector = new StatementCollector(messageBusMock, parametersService);
    }

    public void testSingleQuery() {
        parametersService.registerParametersProvider(emptyParametersProvider);
        PsiFile psiFile = myFixture.configureByText("test.cyp", "MATCH (n) RETURN n;");
        psiFile.accept(statementCollector);

        verify(eventMock, times(0)).handleError(any(), any());

        assertThat(statementCollector.hasErrors())
            .isFalse();
        assertThat(statementCollector.getParameters())
            .isEmpty();
        assertThat(statementCollector.getQueries())
            .containsExactly("MATCH (n) RETURN n;");
    }

    public void testMultipleQueriesInOneLine() {
        parametersService.registerParametersProvider(emptyParametersProvider);
        PsiFile psiFile = myFixture.configureByText("test.cyp", "MATCH (n) RETURN n;MATCH (m) RETURN m;");
        psiFile.acceptChildren(statementCollector);

        verify(eventMock, times(0)).handleError(any(), any());

        assertThat(statementCollector.hasErrors())
            .isFalse();
        assertThat(statementCollector.getParameters())
            .isEmpty();
        assertThat(statementCollector.getQueries())
            .containsExactly("MATCH (n) RETURN n;", "MATCH (m) RETURN m;");
    }

    public void testOneQueryWithError() {
        parametersService.registerParametersProvider(emptyParametersProvider);
        PsiFile psiFile = myFixture.configureByText("test.cyp", "MATCH () ETURN n;");
        psiFile.accept(statementCollector);

        verify(eventMock, times(0)).handleError(any(), any());

        assertThat(statementCollector.hasErrors())
            .isTrue();
        assertThat(statementCollector.getParameters())
            .isEmpty();
        assertThat(statementCollector.getQueries())
            .isEmpty();
    }

    public void testMultipleQueriesInDifferentLinesWithError() {
        parametersService.registerParametersProvider(emptyParametersProvider);
        PsiFile psiFile = myFixture.configureByText("test.cyp", "MATCH (n) RETURN *;\nMATCH () ETURN n;");
        psiFile.accept(statementCollector);

        verify(eventMock, times(0)).handleError(any(), any());

        assertThat(statementCollector.hasErrors())
            .isTrue();
        assertThat(statementCollector.getParameters())
            .isEmpty();
        assertThat(statementCollector.getQueries())
            .isEmpty();
    }

    public void testMultipleCorrectQueriesInDifferentLines() {
        parametersService.registerParametersProvider(emptyParametersProvider);
        PsiFile psiFile = myFixture.configureByText("test.cyp", "MATCH (n) RETURN *;\nMATCH (m) RETURN m;");
        psiFile.accept(statementCollector);

        verify(eventMock, times(0)).handleError(any(), any());

        assertThat(statementCollector.hasErrors())
            .isFalse();
        assertThat(statementCollector.getParameters())
            .isEmpty();
        assertThat(statementCollector.getQueries())
            .containsExactly("MATCH (n) RETURN *;", "MATCH (m) RETURN m;");
    }

    public void testMultipleCorrectQueriesInDifferentLinesWithParameters() {
        parametersService.registerParametersProvider(validParametersProvider);
        PsiFile psiFile = myFixture.configureByText("test.cyp", "CREATE (n {name: $name});\nMATCH (m) RETURN m;");
        psiFile.accept(statementCollector);

        verify(eventMock, times(0)).handleError(any(), any());

        assertThat(statementCollector.hasErrors())
            .isFalse();
        assertThat(statementCollector.getParameters())
            .containsValues("Andrew");
        assertThat(statementCollector.getQueries())
            .containsExactly("CREATE (n {name: $name});", "MATCH (m) RETURN m;");
    }

    public void testParameterExtractionErrorOccurs() {
        parametersService.registerParametersProvider(wrongParametersProvider);
        PsiFile psiFile = myFixture.configureByText("test.cyp", "CREATE (n {name: $name});\nMATCH (m) RETURN m;");
        psiFile.accept(statementCollector);

        verify(eventMock, times(1)).handleError(any(), any());

        assertThat(statementCollector.hasErrors())
            .isTrue();
        assertThat(statementCollector.getParameters())
            .isEmpty();
        assertThat(statementCollector.getQueries())
            .isEmpty();
    }
}
