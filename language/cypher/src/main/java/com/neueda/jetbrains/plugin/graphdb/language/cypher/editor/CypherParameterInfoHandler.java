package com.neueda.jetbrains.plugin.graphdb.language.cypher.editor;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.lang.parameterInfo.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.ui.JBColor;
import com.intellij.util.ArrayUtil;
import com.intellij.util.containers.ContainerUtil;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.elements.InvokableInformation;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.CypherTypes;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.references.CypherInvocation;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.Set;

public class CypherParameterInfoHandler
        implements ParameterInfoHandlerWithTabActionSupport<CypherInvocation, CypherInvocation, PsiElement> {

    @NotNull
    @Override
    @SuppressWarnings("unchecked")
    public PsiElement[] getActualParameters(@NotNull CypherInvocation o) {
        return ArrayUtil.toObjectArray(o.arguments(), PsiElement.class);
    }

    @NotNull
    @Override
    public IElementType getActualParameterDelimiterType() {
        return CypherTypes.OP_COMMA;
    }

    @NotNull
    @Override
    public IElementType getActualParametersRBraceType() {
        return CypherTypes.PARENTHESE_CLOSE;
    }

    @NotNull
    @Override
    public Set<Class> getArgumentListAllowedParentClasses() {
        return ContainerUtil.newHashSet();
    }

    @NotNull
    @Override
    public Set<? extends Class> getArgListStopSearchClasses() {
        return ContainerUtil.newHashSet();
    }

    @NotNull
    @Override
    public Class<CypherInvocation> getArgumentListClass() {
        return CypherInvocation.class;
    }

    @Override
    public boolean couldShowInLookup() {
        return true;
    }

    @Nullable
    @Override
    public Object[] getParametersForLookup(LookupElement item, ParameterInfoContext context) {
        return ArrayUtil.EMPTY_OBJECT_ARRAY;
    }

    @Nullable
    @Override
    public Object[] getParametersForDocumentation(CypherInvocation p, ParameterInfoContext context) {
        return ArrayUtil.EMPTY_OBJECT_ARRAY;
    }

    @Nullable
    @Override
    public CypherInvocation findElementForParameterInfo(@NotNull CreateParameterInfoContext context) {
        PsiElement at = context.getFile().findElementAt(context.getOffset());
        return PsiTreeUtil.getParentOfType(at, CypherInvocation.class);
    }

    @Override
    public void showParameterInfo(@NotNull CypherInvocation ci, @NotNull CreateParameterInfoContext context) {
        context.setItemsToShow(new Object[]{ci});
        context.showHint(ci, ci.getTextRange().getStartOffset(), this);
    }

    @Nullable
    @Override
    public CypherInvocation findElementForUpdatingParameterInfo(@NotNull UpdateParameterInfoContext context) {
        PsiElement at = context.getFile().findElementAt(context.getOffset());
        return PsiTreeUtil.getParentOfType(at, CypherInvocation.class);
    }

    @Override
    public void updateParameterInfo(@NotNull CypherInvocation o, @NotNull UpdateParameterInfoContext context) {
        context.setCurrentParameter(
                ParameterInfoUtils.getCurrentParameterIndex(
                        o.argumentsToken().getNode(),
                        context.getOffset(),
                        CypherTypes.OP_COMMA));
    }

    @Nullable
    @Override
    public String getParameterCloseChars() {
        return ",(";
    }

    @Override
    public boolean tracksParameterIndex() {
        return true;
    }


    @Override
    public void updateUI(CypherInvocation ci, @NotNull ParameterInfoUIContext context) {
        getPresentation(ci, context);
    }

    public String getPresentation(CypherInvocation ci, @NotNull ParameterInfoUIContext context) {
        if (ci == null) {
            context.setUIComponentEnabled(false);
            return null;
        }

        String signature = ci.resolve().stream()
                .findFirst()
                .map(InvokableInformation::getSignature)
                .orElse(null);

        int current = context.getCurrentParameterIndex();

        int start = 0;
        int end = 0;
        if (signature == null) {
            String message = "unknown parameters";
            return context.setupUIComponentPresentation(message, start, end, false, false, false, JBColor.RED);
        }
        if (Objects.equals(signature, "()")) {
            String message = "no parameters";
            return context.setupUIComponentPresentation(message, start, end, false, false, false, JBColor.RED);
        }

        String stripped = signature.substring(1, signature.length() - 1) + ",";
        int from = StringUtils.ordinalIndexOf(stripped, ",", current) + 2;
        int to = StringUtils.ordinalIndexOf(stripped, ",", current + 1) + 1;

        return context.setupUIComponentPresentation(
                signature,
                from, to,
                false, false, false,
                context.getDefaultParameterColor());
    }

}
