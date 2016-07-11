package com.neueda.jetbrains.plugin.graphdb.jetbrains.component.analytics;

import com.brsanthu.googleanalytics.GoogleAnalytics;

import java.util.concurrent.TimeUnit;

public class CustomGoogleAnalytics extends GoogleAnalytics{

    public CustomGoogleAnalytics(String trackingId) {
        super(trackingId);
    }

    @Override
    public void close() {
        try {
            getExecutor().awaitTermination(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            // ignore
        }
        super.close();
    }
}
