package echoserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketWrapper implements SocketWrapper {
    private BufferedReader input;
    private PrintWriter output;
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
            buildIOStream();
        } catch (IOException e) {
            System.out.println("Connection unsuccessful");
        }
        return clientSocket;
    }

    public void buildIOStream() throws IOException {
        createSocketWriter(clientSocket);
        createSocketReader(clientSocket);
    }

    public String receiveData() {
        String inputLine;
        try {
            inputLine = readFromInputStream();
            return inputLine;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void sendData(String data) {
        writeToOutputStream(data);
    }

    public static boolean quit(String keyword) {
        keyword = keyword.toLowerCase();
        return (keyword.equals("q") || keyword.equals("quit"));
    }

    public void close() {
        try {
            input.close();
            output.close();
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createSocketWriter(Socket clientSocket) throws IOException {
        output = new PrintWriter(clientSocket.getOutputStream(), true);
    }

    private void createSocketReader(Socket clientSocket) throws IOException {
        input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    private String readFromInputStream() throws IOException {
        return input.readLine();
    }

    private void writeToOutputStream(String data) {
        if (!quit(data)) {
            System.out.println("Client: " + data);
            output.println("Echo: " + data);
        }
    }
}
