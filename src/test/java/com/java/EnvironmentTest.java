package com.java;

import org.junit.jupiter.api.Test;
import java.io.InputStream;
import java.util.Properties;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnvironmentTest {
    private Properties loadProperties(String env) throws Exception {
        Properties props = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(env + ".properties")) {
            if (input != null) {
                props.load(input);
            } else {
                throw new IllegalArgumentException("Properties file not found for environment: " + env);
            }
        }
        return props;
    }

    @Test
    public void testQaEnvironment() throws Exception {
        Properties qaProps = loadProperties("qa");
        assertEquals("qa", qaProps.getProperty("env.name"));
        assertEquals("https://qa-api.example.com", qaProps.getProperty("env.url"));
    }

    @Test
    public void testProdEnvironment() throws Exception {
        Properties prodProps = loadProperties("prod");
        assertEquals("prod", prodProps.getProperty("env.name"));
        assertEquals("https://api.example.com", prodProps.getProperty("env.url"));
    }
}