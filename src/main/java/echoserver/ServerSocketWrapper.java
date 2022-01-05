package echoserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketWrapper implements SocketWrapper {
    private static BufferedReader input;
    private static PrintWriter output;
    private static Socket clientSocket;
    private static ServerSocket serverSocket;

    public ServerSocket startServerSocket(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        return serverSocket;
    }

    public Socket connectToClient(ServerSocket serverSocket) throws IOException {
        try {
            clientSocket = serverSocket.accept();
            System.out.println("Connection successful");
        } catch (IOException e) {
            System.out.println("Connection unsuccessful");
        }
        return clientSocket;
    }

    public void close() throws IOException {
        input.close();
        output.close();
        clientSocket.close();
        serverSocket.close();
    }
}
