package com.neueda.jetbrains.plugin.graphdb.jetbrains.linemarker;

import com.intellij.execution.lineMarker.RunLineMarkerContributor;
import com.intellij.icons.AllIcons;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.actions.execute.GutterQueryExecutionAction;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.CypherTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CypherQueryLineMarkerContributor extends RunLineMarkerContributor {

    @Nullable
    @Override
    public Info getInfo(@NotNull PsiElement element) {
        if (CypherTypes.SINGLE_QUERY.equals(extractElementType(element))) {
            return new Info(
                    AllIcons.RunConfigurations.TestState.Run,
                    (e) -> "Execute query",
                    createExecutionAction(element)
            );
        }
        return null;
    }



    private GutterQueryExecutionAction createExecutionAction(PsiElement element) {
        return new GutterQueryExecutionAction("Execute query",
                null,
                null,
                element.getText());
    }

    private IElementType extractElementType(PsiElement element) {
        return element.getNode().getElementType();
    }

}
