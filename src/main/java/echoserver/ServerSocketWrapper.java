package echoserver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketWrapper implements SocketWrapper {
    private BufferedReader input;
    private PrintWriter output;
    private Socket clientSocket;
    private ServerSocket serverSocket;

    public ServerSocket startServerSocket(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("Listening for connection on port " + port);
        return serverSocket;
    }

    public Socket connectToClient(ServerSocket serverSocket) {
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
        createWriter(clientSocket);
        createReader(clientSocket);
    }

    public String receiveData() {
        try {
            String inputLine = readFromInputStream();
            System.out.println("Client message: " + inputLine);
            return inputLine;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void sendData(String data) {
        writeToOutputStream(data);
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

    private void createWriter(Socket clientSocket) throws IOException {
        output = new PrintWriter(clientSocket.getOutputStream(), true);
    }

    private void createReader(Socket clientSocket) throws IOException {
        input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    private String readFromInputStream() throws IOException {
        return input.readLine();
    }

    private void writeToOutputStream(String data) {
        if (!Utils.quit(data)) {
            output.println("Echo: " + data);
        }
    }
}
