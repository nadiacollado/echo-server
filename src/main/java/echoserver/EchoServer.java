package echoserver;

import java.io.*;
import java.net.*;

public class EchoServer {
    static int port = 8080;
    static SocketWrapper socketWrapper;
    static ServerSocket serverSocket;

    public EchoServer(int port, SocketWrapper socketWrapper) {
        this.port = port;
        this.socketWrapper = socketWrapper;
    }

    public EchoServer(SocketWrapper socketWrapper) {
        this.socketWrapper = socketWrapper;
    }

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
            System.out.println("Cannot create server socket.");
        }
    }

    public static void startEchoServer(SocketWrapper socketWrapper) throws IOException {
        try {
            serverSocket = socketWrapper.startServerSocket(port);
            System.out.println("Listening for connection on port " + port);
            socketWrapper.connectToClient(serverSocket);
            String clientData;

            while ((clientData = socketWrapper.receiveData()) != null) {
                socketWrapper.sendData(clientData);

                if (socketWrapper.quit(clientData)) {
                    socketWrapper.close();
                }
            }
            socketWrapper.close();
        } catch (IOException e) {
            System.out.println("Issue trying to listen on port " + port);
        }
    }
}