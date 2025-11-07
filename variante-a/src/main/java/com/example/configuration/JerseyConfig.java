package com.example.configuration;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        packages("com.example.Controllers"); // tes REST endpoints
        register(JacksonFeature.class); // ça active Jackson JSON sans JAXB
    }
}
