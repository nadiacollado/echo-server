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
            ClientSocketWrapper clientSocketWrapper = new ClientSocketWrapper();
            startEchoServer(serverSocketWrapper, clientSocketWrapper);
        } catch (IOException e) {
            System.out.println("Cannot create server socket");
        }
    }

    public static void startEchoServer(ServerSocketWrapper serverSocketWrapper, ClientSocketWrapper clientSocketWrapper) throws IOException {
        try {
            serverSocket = serverSocketWrapper.startServerSocket(port);
            System.out.println("Listening for connection on port " + port);

            while (true) {
                Socket clientSocket = serverSocketWrapper.connectToClient(serverSocket);
                clientSocketWrapper.startSocketIO(clientSocket);
            }

        } catch (IOException e) {
            System.out.println("Issue trying to listen on port " + port);
        }
        serverSocket.close();
    }
}