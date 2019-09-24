package com.neueda.jetbrains.plugin.graphdb.jetbrains.util;

import com.intellij.openapi.application.ApplicationManager;

import java.util.concurrent.Callable;
import java.util.function.Consumer;

public class TaskExecutor implements Executor {

    public static final String SYNC_TASK_EXECUTION = "syncTaskExecution";

    public static Executor getInstance() {
        return ExecutorHolder.INSTANCE;
    }

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

    private static class SyncTaskExecutor implements Executor {
        public <T> void runInBackground(Callable<T> task, Consumer<T> onSuccess, Consumer<Exception> onFailure) {
            try {
                T result = task.call();
                onSuccess.accept(result);
            } catch (Exception e) {
                onFailure.accept(e);
            }
        }
    }

    private static class ExecutorHolder {
        static final Executor INSTANCE =
                System.getProperty(SYNC_TASK_EXECUTION) == null ? new TaskExecutor() : new SyncTaskExecutor();
    }

}
