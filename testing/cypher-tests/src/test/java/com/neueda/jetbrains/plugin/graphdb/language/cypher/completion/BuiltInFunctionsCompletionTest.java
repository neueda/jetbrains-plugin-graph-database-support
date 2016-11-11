package com.neueda.jetbrains.plugin.graphdb.language.cypher.completion;

import com.intellij.codeInsight.completion.CompletionType;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.lang.CypherAtoms;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.util.BaseCompletionTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BuiltInFunctionsCompletionTest extends BaseCompletionTest {

    public BuiltInFunctionsCompletionTest() {
        super("builtin_functions");
    }

    public void testBuiltIn() throws Exception {
        myFixture.configureByFiles("BuiltIn.cyp");
        myFixture.complete(CompletionType.BASIC);
        List<String> strings = myFixture.getLookupElementStrings();
        assertThat(strings).containsAll(CypherAtoms.FUNCTIONS);
    }
}
