package com.neueda.jetbrains.plugin.graphdb.language.cypher.completion;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import com.intellij.codeInsight.completion.CompletionType;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.lang.CypherRegexp;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.util.BaseCompletionTest;

public class FunctionsCompletionTest extends BaseCompletionTest {

    public FunctionsCompletionTest() {
        super("functions");
    }

    public void testBuiltIn() throws Exception {
        myFixture.configureByFiles("BuiltIn.cyp");
        myFixture.complete(CompletionType.BASIC, 1);
        List<String> strings = myFixture.getLookupElementStrings();
        assertThat(strings).containsAll(CypherRegexp.FUNCTIONS);
    }
}
