package com.neueda.jetbrains.plugin.graphdb.jetbrains.inspection;

import com.intellij.codeInspection.LocalInspectionTool;
import com.intellij.codeInspection.LocalInspectionToolSession;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.neueda.jetbrains.plugin.graphdb.database.api.GraphDatabaseApi;
import com.neueda.jetbrains.plugin.graphdb.database.api.query.GraphQueryResult;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.DataSourcesComponent;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.database.DatabaseManagerService;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.util.NameUtil;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.CypherTypes;
import com.neueda.jetbrains.plugin.graphdb.platform.GraphConstants;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.neo4j.driver.v1.exceptions.ClientException;

import java.util.Objects;
import java.util.Optional;

public class CypherExplainWarningInspection extends LocalInspectionTool {

    private DatabaseManagerService service;

    public CypherExplainWarningInspection() {
        this.service = ServiceManager.getService(DatabaseManagerService.class);
    }

    @NotNull
    @Override
    public PsiElementVisitor buildVisitor(@NotNull ProblemsHolder holder, boolean isOnTheFly,
                                          @NotNull LocalInspectionToolSession session) {
        return new PsiElementVisitor() {
            @Override
            public void visitElement(PsiElement element) {
                checkStatement(element, holder);
            }
        };
    }

    @NotNull
    @Override
    public PsiElementVisitor buildVisitor(@NotNull ProblemsHolder holder, boolean isOnTheFly) {
        return new PsiElementVisitor() {
            @Override
            public void visitElement(PsiElement element) {
                checkStatement(element, holder);
            }
        };
    }

    private void checkStatement(@NotNull PsiElement statement, @NotNull ProblemsHolder problemsHolder) {
        if (statement.getNode().getElementType() == CypherTypes.SINGLE_QUERY) {

            String fileName = statement.getContainingFile().getName();
            if (fileName.startsWith(GraphConstants.BOUND_DATA_SOURCE_PREFIX)) {
                DataSourcesComponent component = statement.getProject().getComponent(DataSourcesComponent.class);

                component.getDataSourceContainer()
                        .findDataSource(NameUtil.extractDataSourceUUID(fileName))
                        .map(service::getDatabaseFor)
                        .map(api -> this.executeExplainQuery(api, statement.getText()))
                        .filter(Objects::nonNull)
                        .map(GraphQueryResult::getNotifications)
                        .filter(list -> !list.isEmpty())
                        .ifPresent(notifications -> notifications.forEach(notification -> {
                            PsiElement elementAt = Optional.ofNullable(notification.getPositionOffset())
                                    .filter(position -> position > 0)
                                    .map(statement::findElementAt)
                                    .orElse(statement);

                            problemsHolder.registerProblem(elementAt, notification.getTitle());
                        }));
            }
        }
    }

    @Nullable
    @Override
    public String loadDescription() {
        return super.loadDescription();
    }

    private GraphQueryResult executeExplainQuery(GraphDatabaseApi api, String query) {
        try {
            return api.execute("EXPLAIN " + query);
        } catch (ClientException ex) {
            return null;
        }
    }

}
