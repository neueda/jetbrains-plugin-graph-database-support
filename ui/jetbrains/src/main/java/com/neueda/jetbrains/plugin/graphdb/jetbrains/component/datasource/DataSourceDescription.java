package com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource;

import icons.GraphIcons;

import javax.swing.Icon;

public interface DataSourceDescription {

    DataSourceType getType();

    String geTypeName();

    Icon getIcon();

    String getDefaultFileExtension();

    DataSourceDescription NEO4J_BOLT = new DataSourceDescription() {
        @Override
        public DataSourceType getType() {
            return DataSourceType.NEO4J_BOLT;
        }

        @Override
        public Icon getIcon() {
            return GraphIcons.Database.NEO4J;
        }

        @Override
        public String getDefaultFileExtension() {
            return "cypher";
        }

        @Override
        public String geTypeName() {
            return "Neo4j - Bolt";
        }
    };
}
