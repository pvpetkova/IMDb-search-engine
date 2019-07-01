package cleancode.imdb.server;

import cleancode.imdb.config.ConfigHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements Runnable {
    private final ExecutorService mExecutorService;
    private ServerSocket mServerSocket;

    private Server(ServerSocket socket, int threadsNumber) {
        mServerSocket = socket;
        mExecutorService = Executors.newFixedThreadPool(threadsNumber);
    }

    public static void main(String[] args) {
        Server server;
        try {
            ServerSocket serverSocket = new ServerSocket(ConfigHandler.getInstance().getPort());
            server = new Server(serverSocket, ConfigHandler.getInstance().getThreadsNumber());
            Thread thread = new Thread(server);
            thread.start();
        } catch (IOException e) {
            System.out.println("Could not open server socket.");
            e.printStackTrace();
            return;
        }

        String line;
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            do {
                line = bufferedReader.readLine();
                System.out.println(line);
            } while (line != null && !line.trim().equals("stop"));
        } catch (IOException e) {
            System.out.println("Error occurred while reading from the standard input.");
            e.printStackTrace();
        }

        try {
            server.stop();
        } catch (IOException e) {
            System.out.println("Error occurred while closing socket.");
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            mExecutorService.submit(new ServerHandler(mServerSocket.accept()));
        } catch (IOException e) {
            System.out.println("Error occurred while accepting connection.");
            e.printStackTrace();
        }
    }

    private void stop() throws IOException {
        mExecutorService.shutdown();
        mServerSocket.close();
    }

}
