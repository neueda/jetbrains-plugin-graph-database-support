package com.neueda.jetbrains.plugin.graphdb.language.cypher.parser.rename;

import com.neueda.jetbrains.plugin.graphdb.language.cypher.parser.util.BaseRenameTest;

public class IdentifierRenameTest extends BaseRenameTest {

    public void testIdentifier() {
        myFixture.configureByFiles("Identifier.cyp");
        myFixture.renameElementAtCaret("renamed");
        myFixture.checkResultByFile("IdentifierAfter.cyp", false);
    }
}
