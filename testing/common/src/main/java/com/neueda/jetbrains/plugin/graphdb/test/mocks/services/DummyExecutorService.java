package com.neueda.jetbrains.plugin.graphdb.test.mocks.services;

import com.intellij.openapi.application.ApplicationManager;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.services.ExecutorService;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.services.ExecutorServiceInternal;
import org.picocontainer.MutablePicoContainer;

import java.util.concurrent.Callable;
import java.util.function.Consumer;

public class DummyExecutorService implements ExecutorService, ExecutorServiceInternal {

    public static void register() {
        MutablePicoContainer container = (MutablePicoContainer) ApplicationManager.getApplication().getPicoContainer();

        container.unregisterComponent(ExecutorServiceInternal.class.getName());
        container.registerComponentInstance(ExecutorServiceInternal.class.getName(), new DummyExecutorService());
    }

    @Override
    public <T> void runInBackground(Callable<T> task, Consumer<T> onSuccess, Consumer<Exception> onFailure) {
        try {
            T result = task.call();
            onSuccess.accept(result);
        } catch (Exception e) {
            onFailure.accept(e);
        }
    }
}
