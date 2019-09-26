package com.neueda.jetbrains.plugin.graphdb.jetbrains.services;

import com.intellij.openapi.components.ServiceManager;

import java.util.concurrent.Callable;
import java.util.function.Consumer;

public class ExecutorServiceImpl implements ExecutorService {

    @Override
    public <T> void runInBackground(Callable<T> task, Consumer<T> onSuccess, Consumer<Exception> onFailure) {
        ServiceManager.getService(ExecutorServiceInternal.class)
                .runInBackground(task, onSuccess, onFailure);
    }
}
