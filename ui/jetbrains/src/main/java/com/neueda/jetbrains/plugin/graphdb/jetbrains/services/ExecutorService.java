package com.neueda.jetbrains.plugin.graphdb.jetbrains.services;

import java.util.concurrent.Callable;
import java.util.function.Consumer;

public interface ExecutorService {
    <T> void runInBackground(Callable<T> task, Consumer<T> onSuccess, Consumer<Exception> onFailure);
}
