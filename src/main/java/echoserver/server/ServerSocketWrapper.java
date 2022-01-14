package echoserver.server;

import echoserver.Utils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketWrapper implements SocketWrapper {
    private BufferedReader input;
    private PrintWriter output;
    private ServerSocket serverSocket;
    private Socket clientSocket;

    public ServerSocket startServerSocket(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        Utils.print("Listening for connection on port " + port);
        return serverSocket;
    }

    public Socket connectToClient(ServerSocket serverSocket) {
        try {
            clientSocket = serverSocket.accept();
            Utils.print("Connection successful");
            buildIOStream();
        } catch (IOException e) {
            Utils.print("Connection unsuccessful");
        }
        return clientSocket;
    }

    public void buildIOStream() throws IOException {
        createWriter();
        createReader();
    }

    public String receiveData() {
        try {
            String clientInput = input.readLine();
            Utils.print("Client: " + clientInput);
            return clientInput;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void sendData(String data) {
        if (!Utils.quit(data)) {
            output.println(data);
        }
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

    private void createWriter() throws IOException {
        output = new PrintWriter(clientSocket.getOutputStream(), true);
    }

    private void createReader() throws IOException {
        input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }
}
