package cleancode.imdb.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements Runnable {
    private final ExecutorService mExecutorService;
    private ServerSocket mServerSocket;

    private Server(ServerSocket aServerSocket, int aThreadsNumber) {
        mServerSocket = aServerSocket;
        mExecutorService = Executors.newFixedThreadPool(aThreadsNumber);
    }

    public static void main(String[] args) {
        //configuration
        Properties properties = new Properties();
        try (InputStream inputStream = Server.class.getResourceAsStream("/server-config.properties")) {
            properties.load(inputStream);
        } catch (IOException aE) {
            System.out.println("Could not load properties from configuration file.");
            aE.printStackTrace();
        }

        int port = Integer.valueOf(properties.getProperty("server.port", "8888"));
        int threadsNumber = Integer.valueOf(properties.getProperty("server.threadsNumber", "1"));

        Server server;
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            server = new Server(serverSocket, threadsNumber);
            Thread thread = new Thread(server);
            thread.start();
        } catch (IOException aE) {
            System.out.println("Could not open server socket.");
            aE.printStackTrace();
            return;
        }

        String line;
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            do {
                line = bufferedReader.readLine();
            } while (line != null && !line.trim().equals("stop"));
        } catch (IOException aE) {
            System.out.println("Error occurred while reading from the standard input.");
            aE.printStackTrace();
        }

        try {
            server.stop();
        } catch (IOException aE) {
            System.out.println("Error occurred while closing socket.");
            aE.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            mExecutorService.submit(new ServerHandler(mServerSocket.accept()));
        } catch (IOException aE) {
            System.out.println("Error occurred while accepting connection.");
            aE.printStackTrace();
        }
    }

    private void stop() throws IOException {
        mExecutorService.shutdown();
        mServerSocket.close();
    }

}
