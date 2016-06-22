package com.neueda.jetbrains.plugin.graphdb.jetbrains.actions.execute;

import com.intellij.openapi.actionSystem.ActionPromoter;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.psi.PsiFile;
import com.neueda.jetbrains.plugin.graphdb.platform.GraphLanguages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExecuteQueryActionPromoter implements ActionPromoter {

    @Override
    public List<AnAction> promote(List<AnAction> actions, DataContext context) {
        PsiFile psiFile = PlatformDataKeys.PSI_FILE.getData(context);
        if (psiFile != null) {
            if (GraphLanguages.SUPPORTED_LANGUAGES.contains(psiFile.getLanguage().getID())) {
                return checkForExecuteQueryAction(actions);
            }
        }

        return Collections.emptyList();
    }

    private ArrayList<AnAction> checkForExecuteQueryAction(List<AnAction> actions) {
        ArrayList<AnAction> selectedActions = new ArrayList<>();

        for (AnAction action : actions) {
            if (action instanceof ExecuteQueryAction) {
                selectedActions.add(action);
            }
        }
        return selectedActions;
    }
}
