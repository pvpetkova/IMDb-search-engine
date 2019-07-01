package cleancode.imdb.server;

import cleancode.imdb.command.Command;
import cleancode.imdb.command.GetMovie;
import cleancode.imdb.command.GetTVSeries;
import cleancode.imdb.command.Help;
import cleancode.imdb.search.LocalFilesSearcher;
import cleancode.imdb.search.RESTSearcher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cleancode.imdb.command.CommandTokenizer.tokenize;

public class ServerHandler implements Runnable {
    private Socket mSocket;
    private static Map<String, Command> commands;

    public ServerHandler(Socket socket) {
        mSocket = socket;
        LocalFilesSearcher localFilesSearcher = new LocalFilesSearcher();
        RESTSearcher restSearcher = new RESTSearcher();
        commands = new HashMap<>();
        commands.put("get-movie", new GetMovie(localFilesSearcher, restSearcher));
        commands.put("get-tv-series", new GetTVSeries(localFilesSearcher, restSearcher));
    }

    @Override
    public void run() {
        System.out.println("client accepted");
        try (BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(
                        mSocket.getInputStream()));
             PrintWriter printWriter = new PrintWriter(mSocket.getOutputStream()))
        {
            String requestMsg;
            while ((requestMsg = bufferedReader.readLine()) != null || !requestMsg.trim().equals("stop")) {
//                System.out.println("request msg:");
//                System.out.println(requestMsg);
//                //List<String> arguments = tokenize(requestMsg);
//                //String responseMsg = commands.getOrDefault(arguments.get(0), Help.getInstance()).execute(arguments);
//                String responseMsg = requestMsg + ", responding";
                printWriter.write("writing to client?");
                printWriter.flush();
            }
        } catch (IOException e) {
            System.out.println("IO error occurred.");
            e.printStackTrace();
        } finally {
            try {
                mSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
