package com.neueda.jetbrains.plugin.graphdb.jetbrains.actions.ui.console;

import com.intellij.util.messages.Topic;

public interface ToggleFileSpecificParametersEvent {

    Topic<ToggleFileSpecificParametersEvent> TOGGLE_FILE_SPECIFIC_PARAMETERS_EVENT_TOPIC =
            Topic.create("GraphDatabaseConsole.ToggleFileSpecificParametersTopic",
                    ToggleFileSpecificParametersEvent.class);

    void toggle(boolean setToUseFileSpecificParams);

}
