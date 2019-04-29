package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.plan;

import com.intellij.icons.AllIcons;
import com.intellij.ide.DefaultTreeExpander;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.DumbAware;
import com.intellij.ui.HighlightableCellRenderer;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.dualView.TreeTableView;
import com.intellij.ui.treeStructure.treetable.ListTreeTableModelOnColumns;
import com.intellij.ui.treeStructure.treetable.TreeTableCellRenderer;
import com.intellij.ui.treeStructure.treetable.TreeTableModel;
import com.neueda.jetbrains.plugin.graphdb.database.api.query.GraphQueryPlan;
import com.neueda.jetbrains.plugin.graphdb.database.api.query.GraphQueryResult;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.table.ColumnResizer;
import com.neueda.jetbrains.plugin.graphdb.platform.ShouldNeverHappenException;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeCellRenderer;
import java.awt.*;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.plan.ColumnDefinitions.getProfileQueryPlanColumns;
import static com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.plan.ColumnDefinitions.getQueryPlanColumns;
import static com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.plan.QueryPlanArgumentKeys.*;

public class QueryPlanPanel implements Disposable {
    private TreeTableView treeTable;

    private String originalQuery;
    private GraphQueryResult result;

    public QueryPlanPanel(String originalQuery, GraphQueryResult result) {
        this.originalQuery = originalQuery;
        this.result = result;
    }

    public void initialize(Container container) {
        JTextArea queryLabel = new JTextArea(originalQuery);
        queryLabel.setRows(3);
        queryLabel.setEditable(false);

        GraphQueryPlan graphQueryPlan = result.getPlan()
                .orElseThrow(() ->
                        new ShouldNeverHappenException("Sergey Ishchenko",
                                "GraphQueryPanel is initialized when explain or profile queries are executed"));

        ListTreeTableModelOnColumns model = createModel(graphQueryPlan, result.isProfilePlan());

        treeTable = new TreeTableView(model);
        treeTable.setAutoCreateColumnsFromModel(true);
        treeTable.setRootVisible(true);
        treeTable.setCellSelectionEnabled(false);
        treeTable.setRowSelectionAllowed(true);
        treeTable.setAutoResizeMode(TreeTableView.AUTO_RESIZE_OFF);

        treeTable.getTree().setShowsRootHandles(true);

        DefaultTreeExpander expander = new DefaultTreeExpander(treeTable.getTree());
        expander.expandAll();
        initTreeCellRenderer(model);

        JBLabel infoLabel = new JBLabel(getStatusText(result));
        infoLabel.setHorizontalAlignment(SwingConstants.LEFT);

        container.add(new JBScrollPane(queryLabel), BorderLayout.NORTH);
        // scroll pane is needed to correctly fit all the tree content and to show table header
        container.add(new JBScrollPane(treeTable, JBScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JBScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.CENTER);
        container.add(infoLabel, BorderLayout.SOUTH);

        new ColumnResizer(treeTable).resize();
    }

    private void initTreeCellRenderer(ListTreeTableModelOnColumns model) {
        ((TreeTableCellRenderer) treeTable.getDefaultRenderer(TreeTableModel.class))
                .setCellRenderer(new TreeCellRenderer() {
                    private final TreeCellRenderer myBaseRenderer = new HighlightableCellRenderer();

                    @Override
                    @SuppressWarnings("unchecked")
                    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected,
                                                                  boolean expanded, boolean leaf, int row,
                                                                  boolean hasFocus) {
                        Object transformedValue = Stream.of(model.getColumns())
                                .filter(col -> TreeTableModel.class == col.getColumnClass())
                                .findAny()
                                .map(col -> col.valueOf(value))
                                .orElseThrow(() ->
                                        new ShouldNeverHappenException("Sergey Ishchenko",
                                                "columns are statically defined, and each column set should contain "
                                                        + "operator column"));

                        JComponent result = (JComponent) myBaseRenderer.getTreeCellRendererComponent(tree,
                                transformedValue, selected, expanded, leaf, row, hasFocus);
                        result.setOpaque(!selected);
                        return result;
                    }
                });
    }

    private ListTreeTableModelOnColumns createModel(GraphQueryPlan plan, boolean isProfilePlan) {
        MutableTreeNode rootNode = new DefaultMutableTreeNode(plan);
        addChildrenPlansToModel(plan, rootNode);

        return new ListTreeTableModelOnColumns(rootNode,
                isProfilePlan ? getProfileQueryPlanColumns() : getQueryPlanColumns());
    }

    private void addChildrenPlansToModel(GraphQueryPlan plan, MutableTreeNode node) {
        plan.children()
                .forEach(p -> {
                    MutableTreeNode childNode = new DefaultMutableTreeNode(p);
                    node.insert(childNode, 0);
                    childNode.setParent(node);
                    addChildrenPlansToModel(p, childNode);
                });
    }

    @Override
    public void dispose() {
    }

    private static long calculateTotalDbHits(GraphQueryPlan plan) {
        long result = (long) plan.getArguments().getOrDefault(DB_HITS.getKey(), 0L);

        return result + Stream.of(plan)
                .map(GraphQueryPlan::children)
                .flatMap(Collection::stream)
                .map(QueryPlanPanel::calculateTotalDbHits)
                .reduce(0L, Long::sum);
    }

    @NotNull
    private static String getStatusText(GraphQueryResult result) {
        StringBuilder sb = new StringBuilder();

        Optional<GraphQueryPlan> plan = result.getPlan();
        if (plan.isPresent()) {
            Map<String, Object> args = plan.get().getArguments();
            sb.append("Cypher version: ").append(args.getOrDefault(VERSION.getKey(), '-')).append(", ")
                    .append("planner: ").append(args.getOrDefault(PLANNER.getKey(), '-'))
                    .append("(").append(args.getOrDefault(PLANNER_IMPL.getKey(), '-')).append("), ")
                    .append("runtime: ").append(args.getOrDefault(RUNTIME.getKey(), '-'));

            if (result.isProfilePlan()) {
                sb.append(", ").append(calculateTotalDbHits(plan.get())).append(" total db hits in ")
                        .append(result.getExecutionTimeMs()).append("ms.");
            }
        }

        return sb.toString();
    }

    public static class CloseTab extends AnAction implements DumbAware {

        @Override
        public void actionPerformed(AnActionEvent e) {
        }

        @Override
        public void update(final AnActionEvent e) {
            e.getPresentation().setIcon(AllIcons.Actions.Close);
            e.getPresentation().setHoveredIcon(AllIcons.Actions.CloseHovered);
            e.getPresentation().setText("Close");
        }
    }
}
