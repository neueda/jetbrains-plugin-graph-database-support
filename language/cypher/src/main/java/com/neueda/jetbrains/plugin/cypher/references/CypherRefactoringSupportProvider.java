package com.neueda.jetbrains.plugin.cypher.references;

import com.intellij.lang.refactoring.RefactoringSupportProvider;
import com.intellij.psi.PsiElement;
import com.neueda.jetbrains.plugin.cypher.psi.CypherLabelName;
import com.neueda.jetbrains.plugin.cypher.psi.CypherPropertyKeyName;
import com.neueda.jetbrains.plugin.cypher.psi.CypherRelTypeName;
import com.neueda.jetbrains.plugin.cypher.psi.CypherVariable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Inline refactoring support.
 *
 * @author dmitry@vrublevsky.me
 */
public class CypherRefactoringSupportProvider extends RefactoringSupportProvider {

    @Override
    public boolean isMemberInplaceRenameAvailable(@NotNull PsiElement element, @Nullable PsiElement context) {
        return element instanceof CypherVariable
                || element instanceof CypherLabelName
                || element instanceof CypherRelTypeName
                || element instanceof CypherPropertyKeyName
                ;
    }
}
