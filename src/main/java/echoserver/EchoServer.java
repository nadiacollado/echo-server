package echoserver;

import java.io.*;
import java.net.*;

public class EchoServer {
    private static int port = 8080;
    private static ServerSocket serverSocket;

    public static void main(String[] args) throws IOException {
        if (args.length >= 1) {
            port = Integer.parseInt(args[0]);
        } else {
            System.out.println("Port not provided. Using port 8080.");
        }

        try {
            ServerSocketWrapper serverSocketWrapper = new ServerSocketWrapper();
            startEchoServer(serverSocketWrapper);
        } catch (IOException e) {
            System.out.println("Cannot create server socket");
        }
    }

    public static void startEchoServer(ServerSocketWrapper serverSocketWrapper) throws IOException {
        try {
            serverSocket = serverSocketWrapper.startServerSocket(port);
            System.out.println("Listening for connection on port " + port);
            serverSocketWrapper.connectToClient(serverSocket);
            String clientData;

            while ((clientData = serverSocketWrapper.receiveData()) != null) {
                serverSocketWrapper.sendData(clientData);

                if (serverSocketWrapper.quit(clientData)) {
                    serverSocketWrapper.close();
                }
            }
            serverSocketWrapper.close();
        } catch (IOException e) {
            System.out.println("Issue trying to listen on port " + port);
        }
    }
}