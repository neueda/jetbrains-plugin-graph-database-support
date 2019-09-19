package icons;

import com.intellij.icons.AllIcons;

import javax.swing.Icon;

import static com.intellij.openapi.util.IconLoader.getIcon;

public final class GraphIcons {
    public static final class Database {
        public static final Icon UNKNOWN = AllIcons.FileTypes.Unknown;
        public static final Icon NEO4J = getIcon("/graphdb/icons/database/neo4j.svg");
        public static final Icon OPENCYPHER = getIcon("/graphdb/icons/language/cypher.svg");
    }

    public static final class Language {
        public static final Icon CYPHER = getIcon("/graphdb/icons/language/cypher.svg");
    }

    public static final class Window {
        public static final Icon GRAPH = getIcon("/META-INF/pluginIcon.svg");
    }

    public static final class Nodes {
        public static final Icon INDEX = AllIcons.Nodes.ResourceBundle;
        public static final Icon CONSTRAINT = AllIcons.Nodes.C_protected;
        public static final Icon LABEL = AllIcons.Nodes.Class;
        public static final Icon RELATIONSHIP_TYPE = AllIcons.Vcs.Arrow_right;
        public static final Icon PROPERTY_KEY = AllIcons.Nodes.Property;
        public static final Icon VARIABLE = AllIcons.Nodes.Variable;
        public static final Icon FUNCTION = AllIcons.Nodes.Function;
        public static final Icon STORED_PROCEDURE = AllIcons.Nodes.Function;
        public static final Icon USER_FUNCTION = AllIcons.Nodes.Function;
    }

    private GraphIcons() {
    }
}
