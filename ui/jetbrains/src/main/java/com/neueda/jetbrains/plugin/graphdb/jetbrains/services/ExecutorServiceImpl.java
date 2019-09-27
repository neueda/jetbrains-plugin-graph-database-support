package com.neueda.jetbrains.plugin.graphdb.jetbrains.services;

import com.intellij.openapi.application.ApplicationManager;

import java.util.concurrent.Callable;
import java.util.function.Consumer;

public class ExecutorServiceImpl implements ExecutorService {

    @Override
    public <T> void runInBackground(Callable<T> task, Consumer<T> onSuccess, Consumer<Exception> onFailure) {
        ApplicationManager.getApplication().executeOnPooledThread(() -> {
            try {
                T result = task.call();
                ApplicationManager.getApplication().invokeLater(() -> onSuccess.accept(result));

            } catch (Exception exception) {
                ApplicationManager.getApplication().invokeLater(() -> onFailure.accept(exception));
            }
        });
    }
}
