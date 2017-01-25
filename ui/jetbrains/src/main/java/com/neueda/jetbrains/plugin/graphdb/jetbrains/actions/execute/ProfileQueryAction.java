package com.neueda.jetbrains.plugin.graphdb.jetbrains.actions.execute;

public class ProfileQueryAction extends ExecuteQueryAction {

    @Override
    protected String decorateQuery(String query) {
        return "PROFILE " + super.decorateQuery(query);
    }
}
