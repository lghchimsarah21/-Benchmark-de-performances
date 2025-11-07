package com.example.test;

import com.example.configuration.JerseyConfig;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.jackson.JacksonFeature;

import java.io.IOException;
import java.net.URI;

public class App1 {
    public static final String BASE_URI = System.getProperty("server.baseUri", "http://0.0.0.0:8080/");

    public static void main(String[] args) throws IOException {
        // Création de la configuration Jersey
        final ResourceConfig rc = new JerseyConfig();

        // Enregistrement de JacksonFeature pour JSON (sans JAXB)
        rc.register(JacksonFeature.class);

        // Création et démarrage du serveur Grizzly
        final HttpServer server = GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);

        // Hook pour shutdown propre
        Runtime.getRuntime().addShutdownHook(new Thread(server::shutdownNow));

        System.out.println("Jersey app started at " + BASE_URI + " (Press Ctrl+C to stop)");

        // Boucle infinie pour garder le serveur en vie
        try {
            Thread.currentThread().join();
        } catch (InterruptedException ignored) {
        }
    }
}
