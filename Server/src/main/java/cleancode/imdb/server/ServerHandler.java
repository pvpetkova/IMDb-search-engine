package cleancode.imdb.server;

import java.net.Socket;

public class ServerHandler implements Runnable {
    Socket mSocket;
    public ServerHandler(Socket aSocket) {
        mSocket = aSocket;
    }

    @Override
    public void run() {
        //TODO: implement run
    }
}
