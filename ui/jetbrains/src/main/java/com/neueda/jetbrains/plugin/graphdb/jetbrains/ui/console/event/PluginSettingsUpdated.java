package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.event;

import com.intellij.util.messages.Topic;

public interface PluginSettingsUpdated {

    Topic<PluginSettingsUpdated> TOPIC = Topic.create("GraphDatabaseConsole.SettingsUpdated", PluginSettingsUpdated.class);

    void settingsUpdated();
}
