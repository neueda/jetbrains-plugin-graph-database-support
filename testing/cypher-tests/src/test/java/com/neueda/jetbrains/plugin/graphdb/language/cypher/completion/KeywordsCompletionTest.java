package com.neueda.jetbrains.plugin.graphdb.language.cypher.completion;

import com.intellij.codeInsight.completion.CompletionType;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.atoms.CypherKeywords;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.util.BaseCompletionTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class KeywordsCompletionTest extends BaseCompletionTest {

    public void testKeywords() throws Exception {
        myFixture.configureByText("test.cyp", "<caret>");
        myFixture.complete(CompletionType.BASIC);
        List<String> strings = myFixture.getLookupElementStrings();
        assertThat(strings)
                .containsAll(CypherKeywords.KEYWORDS);
    }
}
