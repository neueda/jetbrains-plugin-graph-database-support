package com.neueda.jetbrains.plugin.graphdb.jetbrains.linemarker;

import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo;
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider;
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.CypherTypes;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.references.CypherNamedElement;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.util.CypherUtil;
import com.neueda.jetbrains.plugin.graphdb.platform.GraphIcons;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class QueryLineMarkerProvider extends RelatedItemLineMarkerProvider {

    @Override
    protected void collectNavigationMarkers(@NotNull PsiElement element, Collection<? super RelatedItemLineMarkerInfo> result) {
        Optional.of(element)
                .filter(this::isSingleQuery)
                .map(this::createLineMarkerInfo)
                .map(result::add);
    }

    private boolean isSingleQuery(PsiElement element) {
        return CypherTypes.SINGLE_QUERY.equals(extractElementType(element));
    }

    private RelatedItemLineMarkerInfo<PsiElement> createLineMarkerInfo(PsiElement element) {
        Project project = element.getProject();
        List<CypherNamedElement> namedElements = CypherUtil.findAll(project, extractElementType(element));
        return NavigationGutterIconBuilder.create(GraphIcons.Gutter.RUN)
                .setTargets(namedElements)
                .setTooltipText("Execute query")
                .createLineMarkerInfo(element);
    }

    private IElementType extractElementType(PsiElement element) {
        return element.getNode().getElementType();
    }
}
