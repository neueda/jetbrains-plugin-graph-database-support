package com.neueda.jetbrains.plugin.graphdb.language.cypher.references;

import com.intellij.patterns.ElementPattern;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.PsiElement;
import com.intellij.refactoring.rename.RenameInputValidator;
import com.intellij.util.ProcessingContext;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.CypherLanguage;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.file.CypherFile;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.lang.CypherRegexp;

/**
 * Validates identifier renaming.
 *
 * @author dmitry@vrublevsky.me
 */
public class CypherRenameInputValidator implements RenameInputValidator {

    @Override
    public ElementPattern<? extends PsiElement> getPattern() {
        return PlatformPatterns.psiElement().withLanguage(CypherLanguage.INSTANCE);
    }

    @Override
    public boolean isInputValid(String newName, PsiElement element, ProcessingContext context) {
        if (element instanceof CypherFile) {
            // Cypher file can have any name.
            return true;
        }
        return !CypherRegexp.ALL_KEYWORDS.contains(newName.toLowerCase())
                && newName.matches(CypherRegexp.SYMBOLIC_NAME_REGEXP);
    }
}
