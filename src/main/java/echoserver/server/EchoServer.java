package echoserver.server;

import echoserver.Utils;

import java.io.*;
import java.net.*;

public class EchoServer {
    SocketWrapper socketWrapper;

    public EchoServer(SocketWrapper socketWrapper) {
        this.socketWrapper = socketWrapper;
    }

    public void start(int port) {
        try {
            ServerSocket serverSocket = socketWrapper.startServerSocket(port);
            socketWrapper.connectToClient(serverSocket);
            String clientData;

            while ((clientData = socketWrapper.receiveData()) != null) {
                socketWrapper.sendData(clientData);
                if (Utils.quit(clientData)) {
                    socketWrapper.close();
                }
            }
            socketWrapper.close();
        } catch (IOException e) {
            Utils.error(String.format("Issue trying to listen on port %s", port), e);
        }
    }
}