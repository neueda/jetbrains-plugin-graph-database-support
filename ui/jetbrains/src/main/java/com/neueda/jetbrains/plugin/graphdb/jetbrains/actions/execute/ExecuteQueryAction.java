package com.neueda.jetbrains.plugin.graphdb.jetbrains.actions.execute;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiElement;

import static com.neueda.jetbrains.plugin.graphdb.language.cypher.util.PsiTraversalUtilities.Cypher.getCypherStatementAtOffset;
import static com.neueda.jetbrains.plugin.graphdb.platform.SupportedLanguage.isSupported;
import static java.util.Objects.nonNull;

public class ExecuteQueryAction extends AnAction {


    private final ExecuteQueryService executionProvider;

    public ExecuteQueryAction() {
        this.executionProvider = ExecuteQueryService.getInstance();
    }

    @Override
    public void update(AnActionEvent e) {
        Editor editor = e.getData(CommonDataKeys.EDITOR_EVEN_IF_INACTIVE);

        if (nonNull(editor)) {
            e.getPresentation().setEnabled(true);
        } else {
            e.getPresentation().setEnabled(false);
        }
    }

    @Override
    public void actionPerformed(AnActionEvent e) {
        QueryActionWrapper actionWrapper = new QueryActionWrapper(e);

        String query = extractQuery(actionWrapper);
        executionProvider.executeQuery(actionWrapper, decorateQuery(query));
    }

    private String extractQuery(QueryActionWrapper actionWrapper) {
        String query = null;
        if (actionWrapper.getCaret().hasSelection()) {
            query = extractSelectedText(actionWrapper);
        } else if (nonNull(actionWrapper.getPsiFile())) {
            String languageId = actionWrapper.getPsiFile().getLanguage().getID();
            if (isSupported(languageId)) {
                query = extractQueryFromStatement(actionWrapper);
            }
        }
        return query;
    }

    private String extractSelectedText(QueryActionWrapper actionWrapper) {
        return actionWrapper.getCaret().getSelectedText();
    }

    private String extractQueryFromStatement(QueryActionWrapper actionWrapper) {
        PsiElement cypherStatement = getCypherStatementAtOffset(actionWrapper.getPsiFile(), actionWrapper.getCaret().getOffset());
        if (nonNull(cypherStatement)) {
            return cypherStatement.getText();
        }
        return null;
    }

    protected String decorateQuery(String query) {
        return query;
    }
}
