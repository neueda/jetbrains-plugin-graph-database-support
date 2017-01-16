package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.plan;

import com.intellij.ui.treeStructure.treetable.TreeTableModel;
import com.intellij.util.ui.ColumnInfo;
import com.neueda.jetbrains.plugin.graphdb.database.api.query.GraphQueryPlan;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.plan.QueryPlanArgumentKeys.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toSet;

class ColumnDefinitions {
    private static final Set<String> RESERVED_KEYS = Stream.of(QueryPlanArgumentKeys.values())
            .map(QueryPlanArgumentKeys::getKey)
            .collect(toSet());

    private final static ColumnInfo<DefaultMutableTreeNode, String> OPERATOR_COL =
            new ColumnInfo<DefaultMutableTreeNode, String>("Operator") {
                @Override
                public Class<?> getColumnClass() {
                    return TreeTableModel.class;
                }

                @Nullable
                @Override
                public String valueOf(DefaultMutableTreeNode o) {
                    return ((GraphQueryPlan) o.getUserObject()).getOperatorType();
                }
            };

    private final static ColumnInfo<DefaultMutableTreeNode, String> ESTIMATED_ROWS_COL =
            getArgumentColumn("Estimated rows", ESTIMATED_ROWS.getKey(), "0");

    private final static ColumnInfo<DefaultMutableTreeNode, String> DB_HITS_COL =
            getArgumentColumn("DB Hits", DB_HITS.getKey(), "0");

    private final static ColumnInfo<DefaultMutableTreeNode, String> ROWS_COL =
            getArgumentColumn("Rows", ROWS.getKey(), "0");

    private final static ColumnInfo<DefaultMutableTreeNode, String> ARGUMENTS_COL =
            new ColumnInfo<DefaultMutableTreeNode, String>("Arguments") {
                @Nullable
                @Override
                public String valueOf(DefaultMutableTreeNode o) {
                    return ((GraphQueryPlan) o.getUserObject()).getArguments().entrySet().stream()
                            .filter(e -> !RESERVED_KEYS.contains(e.getKey()))
                            .map(Map.Entry::getValue)
                            .map(Object::toString)
                            .collect(joining(", "));
                }
            };

    private final static ColumnInfo<DefaultMutableTreeNode, String> IDENTIFIERS_COL =
            new ColumnInfo<DefaultMutableTreeNode, String>("Identifiers") {
                @Nullable
                @Override
                public String valueOf(DefaultMutableTreeNode o) {
                    return ((GraphQueryPlan) o.getUserObject()).getIdentifiers().stream()
                            .collect(joining(", "));
                }
            };

    static ColumnInfo[] getQueryPlanColumns() {
        return new ColumnInfo[]{
                OPERATOR_COL,
                ESTIMATED_ROWS_COL,
                ARGUMENTS_COL,
                IDENTIFIERS_COL
        };
    }

    static ColumnInfo[] getProfileQueryPlanColumns() {
        return new ColumnInfo[]{
                OPERATOR_COL,
                ROWS_COL,
                DB_HITS_COL,
                ESTIMATED_ROWS_COL,
                ARGUMENTS_COL,
                IDENTIFIERS_COL
        };
    }

    @NotNull
    private static <T> ColumnInfo<DefaultMutableTreeNode, T> getArgumentColumn(String title, String argumentKey,
                                                                               T defaultValue) {
        return new ColumnInfo<DefaultMutableTreeNode, T>(title) {
            @Nullable
            @Override
            public T valueOf(DefaultMutableTreeNode o) {
                return ((GraphQueryPlan) o.getUserObject()).getArguments().entrySet().stream()
                        .filter(e -> e.getKey().equalsIgnoreCase(argumentKey))
                        .map(Map.Entry::getValue)
                        .findFirst()
                        .map(a -> (T) a)
                        .orElse(defaultValue);
            }
        };
    }
}
