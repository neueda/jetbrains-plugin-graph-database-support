package com.neueda.jetbrains.plugin.graphdb.jetbrains.util;

import java.util.concurrent.Callable;
import java.util.function.Consumer;

public interface Executor {
    <T> void runInBackground(Callable<T> task, Consumer<T> onSuccess, Consumer<Exception> onFailure);
}
