package com.neueda.jetbrains.plugin.graphdb.jetbrains.component.linemarker;

import com.intellij.codeInsight.daemon.LineMarkerInfo;
import com.intellij.codeInsight.daemon.LineMarkerProvider;
import com.intellij.icons.AllIcons;
import com.intellij.ide.DataManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.ex.ActionUtil;
import com.intellij.openapi.editor.markup.GutterIconRenderer;
import com.intellij.psi.PsiElement;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.actions.execute.ExecuteQueryAction;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.util.PsiTraversalUtilities;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static java.util.Objects.*;

public class CypherLineMarkerProvider implements LineMarkerProvider {

    /***
     * Line marker cannot be set to CypherStatementItem as it triggers following warning:
     * "Performance warning: LineMarker is supposed to be registered for leaf elements only"
     */
    private static PsiElement getFirstLeaf(@NotNull PsiElement element) {
        PsiElement e = element;
        while (e.getChildren().length > 0) {
            e = e.getFirstChild();
        }
        return  e;
    }

    @Override
    public LineMarkerInfo getLineMarkerInfo(@NotNull final PsiElement element) {
        PsiElement queryElement = PsiTraversalUtilities.Cypher.getCypherStatement(element);
        PsiElement lastQueryChild = isNull(queryElement) ? null : getFirstLeaf(queryElement);
        if (element == lastQueryChild) {
            return new LineMarkerInfo<PsiElement>(element,
                    element.getTextRange(),
                    AllIcons.Actions.Execute,
                    element1 -> "Execute Query",
                    (mouseEvent, psiElement) ->
                            getDataContext().ifPresent(c ->
                                    ActionUtil.invokeAction(new ExecuteQueryAction(queryElement), c, "", mouseEvent, null)),
                    GutterIconRenderer.Alignment.CENTER) {
                @Override
                public GutterIconRenderer createGutterRenderer() {
                    return new LineMarkerGutterIconRenderer<PsiElement>(this) {
                        @Override
                        public AnAction getClickAction() {
                            return new ExecuteQueryAction(queryElement);
                        }

                        @Override
                        public boolean isNavigateAction() {
                            return true;
                        }
                    };
                }
            };
        }
        return null;
    }

    private Optional<DataContext> getDataContext() {
        try {
            return Optional.ofNullable(
                    DataManager.getInstance()
                            .getDataContextFromFocusAsync()
                            .blockingGet(100, TimeUnit.MILLISECONDS));
        } catch (TimeoutException | ExecutionException e) {
            return Optional.empty();
        }

    }

    @Override
    public void collectSlowLineMarkers(@NotNull List<PsiElement> elements, @NotNull Collection<LineMarkerInfo> result) {
        // Method intentionally left empty.
    }
}
