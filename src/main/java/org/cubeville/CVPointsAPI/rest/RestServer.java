package org.cubeville.CVPointsAPI.rest;

import com.sun.net.httpserver.HttpServer;
import org.cubeville.CVPointsAPI.CVPointsAPI;
import org.cubeville.CVPointsAPI.rest.handlers.StatusHandler;
import org.cubeville.CVPointsAPI.rest.handlers.UpdateHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class RestServer {
    private final int port;
    private final int threads;
    public static String privateKey;
    private HttpServer server;

    public RestServer(int port, int threads, String pKey) {
        this.port = port;
        this.threads = threads;
        privateKey = pKey;
    }

    public void start() throws IOException {
        server = HttpServer.create(new InetSocketAddress(this.port), 0);

        server.createContext("/status", new StatusHandler());
        server.createContext("/update", new UpdateHandler());

        // Server threads
        server.setExecutor(Executors.newFixedThreadPool(this.threads));

        server.start();

        CVPointsAPI.getInstance().getLogger().info("REST server started on port " + this.port);
    }

    public void stop() {
        if (server != null) {
            server.stop(0);
            System.out.println("REST server stopped");
        }
    }
}