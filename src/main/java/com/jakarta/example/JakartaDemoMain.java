package com.jakarta.example;

import com.jakarta.example.filter.LoggingFilter;
import com.jakarta.example.h2db.H2InMemoryDBLink;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.grizzly.filterchain.*;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.grizzly.http.server.NetworkListener;
import org.glassfish.grizzly.http.server.StaticHttpHandler;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.h2.tools.Server;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.net.URI;
import java.sql.SQLException;

public class JakartaDemoMain {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass().getName());

    public static final String BASE_URI = "http://localhost:8090/";

    public static HttpServer startServer() {
        final ResourceConfig rc = new ResourceConfig()
                .packages("com.jakarta.example") // Ensure this matches your package name
                .register(JacksonFeature.class) // Register Jackson for JSON support
                .property("jersey.config.server.wadl.disableWadl", false) // Enable WADL generation
                .register(LoggingFilter.class); // Register your logging filter
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    public static void main(String[] args) {
        Server databaseServer = null;
        HttpServer webServer = null;
        try {
            webServer = startServer();
            databaseServer = H2InMemoryDBLink.startDatabaseServer();

            logger.info("H2 Console is available at http://localhost:8082");
            H2InMemoryDBLink.verifyDatabase();

            logger.info(String.format("Jersey app started with WADL available at "
                    + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));

            System.in.read();

        } catch (SQLException | IOException e) {
            logger.error("An error occurred while starting or running the server", e);
        } finally {
            if (databaseServer != null) {
                databaseServer.stop();
            }
            if (webServer != null) {
                webServer.shutdownNow();
            }
        }
    }


}