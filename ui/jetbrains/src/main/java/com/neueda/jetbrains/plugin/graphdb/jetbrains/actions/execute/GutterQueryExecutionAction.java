package com.neueda.jetbrains.plugin.graphdb.jetbrains.actions.execute;

import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Collections;

public class GutterQueryExecutionAction extends ExecuteQueryAction {

    private final String query;

    public GutterQueryExecutionAction(@Nullable String text, @Nullable String description, @Nullable Icon icon, String query) {
        super(text, description, icon);
        this.query = query;
    }

    @Override
    protected ExecuteQueryPayload createQueryPayload(ActionState state) {
        return new ExecuteQueryPayload(query, Collections.emptyMap(), state.getEditor());
    }
}
