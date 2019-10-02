package com.neueda.jetbrains.plugin.graphdb.jetbrains.services;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.ModalityState;

import java.util.concurrent.Callable;
import java.util.function.Consumer;

public class ExecutorServiceImpl implements ExecutorService {

    @Override
    public <T> void runInBackground(Callable<T> task, Consumer<T> onSuccess, Consumer<Exception> onFailure) {
        runInBackground(task, onSuccess, onFailure, ModalityState.defaultModalityState());
    }

    @Override
    public <T> void runInBackground(Callable<T> task, Consumer<T> onSuccess, Consumer<Exception> onFailure, ModalityState modalityState) {
        ApplicationManager.getApplication().executeOnPooledThread(() -> {
            try {
                T result = task.call();
                ApplicationManager.getApplication().invokeLater(() -> onSuccess.accept(result), modalityState);

            } catch (Exception exception) {
                ApplicationManager.getApplication().invokeLater(() -> onFailure.accept(exception), modalityState);
            }
        });
    }
}
