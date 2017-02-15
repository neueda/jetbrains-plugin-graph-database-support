package com.neueda.jetbrains.plugin.graphdb.language.cypher.inspections;

import com.intellij.codeInspection.LocalInspectionTool;
import com.intellij.codeInspection.LocalInspectionToolSession;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.util.Range;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.elements.InvokableInformation;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.CypherFunctionInvocation;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.CypherProcedureInvocation;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.references.CypherInvocation;
import org.jetbrains.annotations.NotNull;

public class CypherFunctionCallInspection extends LocalInspectionTool {

    @NotNull
    @Override
    public PsiElementVisitor buildVisitor(@NotNull ProblemsHolder holder,
                                          boolean isOnTheFly,
                                          @NotNull LocalInspectionToolSession session) {
        return new PsiElementVisitor() {
            @Override
            public void visitElement(PsiElement element) {
                super.visitElement(element);
                if (element instanceof CypherInvocation) {
                    CypherInvocation ci = (CypherInvocation) element;

                    // Skip functions built into grammar, since they are checked by parser
                    if (!(ci instanceof CypherFunctionInvocation) && !(ci instanceof CypherProcedureInvocation)) {
                        return;
                    }

                    InvokableInformation invokable = ci.resolve().orElse(null);
                    if (invokable == null) {
                        return;
                    }

                    int actual = ci.arguments().size();
                    Range<Integer> expected = invokable.getArity();

                    if (expected.isWithin(actual)) {
                        return;
                    }

                    String tail = " arguments in call to " + ci.getFullName();
                    if (actual < expected.getFrom()) {
                        holder.registerProblem(ci.argumentsToken(), "not enough" + tail);
                    } else if (actual > expected.getTo()) {
                        holder.registerProblem(ci.argumentsToken(), "too many" + tail);
                    }
                }
            }
        };
    }
}
