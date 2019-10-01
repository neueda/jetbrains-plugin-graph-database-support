package com.neueda.jetbrains.plugin.graphdb.jetbrains.services;

import com.intellij.openapi.application.ModalityState;

import java.util.concurrent.Callable;
import java.util.function.Consumer;

public interface ExecutorService {
    <T> void runInBackground(Callable<T> task, Consumer<T> onSuccess, Consumer<Exception> onFailure);

    <T> void runInBackground(Callable<T> task, Consumer<T> onSuccess, Consumer<Exception> onFailure, ModalityState modalityState);
}
