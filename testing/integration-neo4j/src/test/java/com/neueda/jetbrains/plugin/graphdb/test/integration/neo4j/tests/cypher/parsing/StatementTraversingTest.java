package com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.tests.cypher.parsing;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.tests.cypher.util.BaseGenericTest;

import static com.neueda.jetbrains.plugin.graphdb.language.cypher.util.PsiTraversalUtilities.Cypher.getCypherStatementAtOffset;
import static org.assertj.core.api.Assertions.assertThat;

public class StatementTraversingTest extends BaseGenericTest {

    private static final String FAKE_CARET_SYMBOL = "|";

    public void testCypherStatementAtOffsetIfCaretNotSet() {
        PsiElement statement = cypherStatementAtOffset("MATCH (n) RETURN n;");
        assertThat(statement).isNull();
    }

    public void testCypherStatementAtOffsetWhereCaretInTheStartOfQuery() {
        PsiElement statement = cypherStatementAtOffset("%sMATCH (n) RETURN n;");
        assertThat(statement.getText()).isEqualTo("MATCH (n) RETURN n;");
    }

    public void testCypherStatementAtOffsetWhereCaretInTheEndOfQuery() {
        PsiElement statement = cypherStatementAtOffset("MATCH (n) RETURN n;%s");
        assertThat(statement.getText()).isEqualTo("MATCH (n) RETURN n;");
    }

    public void testCypherStatementAtOffsetWhereCaretBetweenMultipleQueries() {
        PsiElement statement = cypherStatementAtOffset("MATCH (n) RETURN n;%sRETURN 1;");
        assertThat(statement.getText()).isEqualTo("MATCH (n) RETURN n;");
    }

    public void testCypherStatementAtOffsetWhereCaretInTheEndOfFirstQueryAfterSpaceSymbol() {
        PsiElement statement = cypherStatementAtOffset("MATCH (n) RETURN n; %sRETURN 1;");
        assertThat(statement).isNull();
    }

    public void testCypherStatementAtOffsetWhereCaretInTheMiddleOfQuery() {
        PsiElement statement = cypherStatementAtOffset("MATCH (n) RETURN n; RETUR%sN 1;");
        assertThat(statement.getText()).isEqualTo("RETURN 1;");
    }

    public void testCypherStatementAtOffsetWhereCaretInTheEndOfLastQuery() {
        PsiElement statement = cypherStatementAtOffset("MATCH (n) RETURN n; RETURN 1;%s");
        assertThat(statement.getText()).isEqualTo("RETURN 1;");
    }

    public void testCypherStatementAtOffsetForTheMultilineQuery() {
        PsiElement statement = cypherStatementAtOffset("MATCH (n) \n%sRETURN n;");
        assertThat(statement.getText()).isEqualTo("MATCH (n) \nRETURN n;");
    }

    private PsiElement cypherStatementAtOffset(String queryTemplate) {
        String queryWithCaret = String.format(queryTemplate, FAKE_CARET_SYMBOL);
        String queryWithoutCaret = queryWithCaret.replace(FAKE_CARET_SYMBOL, "");
        int offset = queryWithCaret.indexOf(FAKE_CARET_SYMBOL);

        PsiFile psiFile = myFixture.configureByText("test.cyp", queryWithoutCaret);
        return getCypherStatementAtOffset(psiFile, offset);
    }

}
