package com.neueda.jetbrains.plugin.graphdb.jetbrains.component.updater;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ApplicationComponent;

public interface GraphDatabaseUpdaterComponent extends ApplicationComponent {

    static GraphDatabaseUpdaterComponent getInstance() {
        return ApplicationManager.getApplication().getComponent(GraphDatabaseUpdaterComponent.class);
    }
}
