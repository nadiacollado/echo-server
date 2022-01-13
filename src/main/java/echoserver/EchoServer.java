package echoserver;

import java.io.*;
import java.net.*;

public class EchoServer {
    int port;
    SocketWrapper socketWrapper;
    ServerSocket serverSocket;

    public EchoServer(int port, SocketWrapper socketWrapper) {
        this.port = port;
        this.socketWrapper = socketWrapper;
    }

    public void startEchoServer() {
        try {
            serverSocket = socketWrapper.startServerSocket(port);
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
            System.out.println("Issue trying to listen on port " + port);
        }
    }
}