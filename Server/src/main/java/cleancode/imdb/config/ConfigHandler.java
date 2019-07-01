package cleancode.imdb.config;

import cleancode.imdb.server.Server;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class ConfigHandler {
    private static ConfigHandler instance;
    private String host;
    private int port;
    private int threadsNumber;
    private String apiKey;
    private String apiURL;
    private Path sourceDir;

    private ConfigHandler() {
        Properties properties = loadProperties();
        host = properties.getProperty("server.host", "localhost");
        port = Integer.valueOf(properties.getProperty("server.port", String.valueOf(8888)));
        threadsNumber = Integer.valueOf(properties.getProperty("server.threadsNumber", String.valueOf(1)));
        apiKey = properties.getProperty("apiKey");
        apiURL = properties.getProperty("apiUrl");
        sourceDir = Paths.get(properties.getProperty("source.dir"));
    }

    public static ConfigHandler getInstance() {
        if (instance == null) {
            instance = new ConfigHandler();
        }
        return instance;
    }

    private Properties loadProperties() {
        Properties properties = new Properties();
        try (InputStream inputStream = Server.class.getResourceAsStream("/server-config.properties")) {
            properties.load(inputStream);
        } catch (IOException e) {
            System.out.println("Could not load properties from configuration file.");
            e.printStackTrace();
        }
        return properties;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public int getThreadsNumber() {
        return threadsNumber;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getApiURL() {
        return apiURL;
    }

    public Path getSourceDir() {
        return sourceDir;
    }
}
