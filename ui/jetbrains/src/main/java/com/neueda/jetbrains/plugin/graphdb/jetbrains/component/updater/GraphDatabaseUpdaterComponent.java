package com.neueda.jetbrains.plugin.graphdb.jetbrains.component.updater;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.BaseComponent;

public interface GraphDatabaseUpdaterComponent extends BaseComponent {

    static GraphDatabaseUpdaterComponent getInstance() {
        return ApplicationManager.getApplication().getComponent(GraphDatabaseUpdaterComponent.class);
    }
}
