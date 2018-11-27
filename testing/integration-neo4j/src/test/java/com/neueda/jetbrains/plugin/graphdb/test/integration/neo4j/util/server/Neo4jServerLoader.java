package com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.util.server;

import com.google.common.base.Throwables;
import com.intellij.openapi.util.io.FileUtil;
import com.neueda.jetbrains.plugin.graphdb.test.database.neo4j.common.Neo4jServer;
import org.neo4j.driver.v1.Config;
import org.xeustechnologies.jcl.JarClassLoader;
import org.xeustechnologies.jcl.JclObjectFactory;
import org.xeustechnologies.jcl.proxy.CglibProxyProvider;
import org.xeustechnologies.jcl.proxy.ProxyProviderFactory;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public abstract class Neo4jServerLoader implements Neo4jServer {

    private final ExecutorService executor;
    private Future<Neo4jServer> neo4jServerFuture;
    private Neo4jServer neo4jServer;

    public Neo4jServerLoader() {
        executor = Executors.newFixedThreadPool(1);
    }

    protected abstract String getLibraryPath();

    protected abstract String getNeo4jServerClass();

    @Override
    public void start() {
        neo4jServerFuture = executor.submit(() -> {
            try {
                // Initialize class loader
                JarClassLoader jarClassLoader = new JarClassLoader();
                jarClassLoader.add(getLibraryPath());

                // Initialize class loader object factory
                JclObjectFactory factory = JclObjectFactory.getInstance(true);
                ProxyProviderFactory.setDefaultProxyProvider(new CglibProxyProvider());

                // Set context class loader to our new class loader
                Thread.currentThread().setContextClassLoader(jarClassLoader);

                // Start Neo4j server in separate thread
                Neo4jServer neo4jServer = (Neo4jServer) factory.create(jarClassLoader, getNeo4jServerClass());
                neo4jServer.start();

                return neo4jServer;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        });
    }

    @Override
    public String getBoltHost() {
        return getNeo4jServer().getBoltHost();
    }

    @Override
    public String getBoltPort() {
        return getNeo4jServer().getBoltPort();
    }

    private synchronized Neo4jServer getNeo4jServer() {
        if (neo4jServer == null) {
            try {
                neo4jServer = neo4jServerFuture.get();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            if (neo4jServer == null) {
                throw new IllegalStateException("Neo4j server should not be null");
            }
            cleanupNeo4jKnownHosts(this);
            return neo4jServer;
        }

        return neo4jServer;
    }

    private static synchronized void cleanupNeo4jKnownHosts(Neo4jServerLoader neo4jServerLoader) {
        File hostsFile = Config.defaultConfig().trustStrategy().certFile();
        try {
            if (hostsFile != null && hostsFile.isFile()) {
                List<String> lines = FileUtil.loadLines(hostsFile);
                List<String> updatedLines = lines.stream()
                           .filter((line) -> !line.startsWith(neo4jServerLoader.getBoltHost() + ":" + neo4jServerLoader.getBoltPort()))
                           .filter((line) -> !line.isEmpty())
                           .collect(Collectors.toList());
                FileUtil.writeToFile(hostsFile, String.join(System.lineSeparator(), updatedLines) + System.lineSeparator());
            }
        } catch (Exception e) {
            Throwables.throwIfUnchecked(e);
            throw new RuntimeException(e);
        }
    }
}
