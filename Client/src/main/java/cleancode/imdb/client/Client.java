package cleancode.imdb.client;

import java.io.*;
import java.net.Socket;
import java.util.Properties;

public class Client {
    private static String serverHost;
    private static int serverPort;

    public static void main(String[] args) {
        //Get properties to initialize socket
        initializeProperties();

        try (Socket socket = new Socket(serverHost, serverPort);
             PrintWriter socketOutput =
                     new PrintWriter(socket.getOutputStream(), true);
             BufferedReader socketInput =
                     new BufferedReader(
                             new InputStreamReader(socket.getInputStream()));
             BufferedReader userInput =
                     new BufferedReader(
                             new InputStreamReader(System.in))) {
            String inputLine = "";
            String response;
            while (!inputLine.equals("stop")) {
                inputLine = userInput.readLine();
                socketOutput.write(inputLine);
                socketOutput.flush();
                response = socketInput.readLine();
                System.out.println(response);
            }
            socketOutput.write(inputLine);
            socketOutput.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void initializeProperties() {
        Properties properties = new Properties();
        try (InputStream inputStream = Client.class.getResourceAsStream("/client-config.properties")) {
            properties.load(inputStream);
        } catch (IOException e) {
            System.err.println("Could not load properties from configuration file.");
            e.printStackTrace();
        }
        serverHost = properties.getProperty("server.host", "localhost");
        serverPort = Integer.parseInt(properties.getProperty("server.port", "8888"));
    }
}
