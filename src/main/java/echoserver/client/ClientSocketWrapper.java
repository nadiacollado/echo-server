package echoserver.client;

import echoserver.Utils;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class ClientSocketWrapper implements ClientWrapper {
    private BufferedReader input;
    private PrintWriter output;
    private Socket socket;

    public void startClientSocket(InetAddress host, int port) {
        try {
            socket = new Socket(host, port);
            Utils.print("Connection successful using " + host + " on port " + port);
            buildIOStream();
        } catch (IOException e) {
            Utils.error("Could not create client socket ", e);
        }
    }

    public void buildIOStream() throws IOException {
        createWriter();
        createReader();
    }

    public String getUserInput() {
        BufferedReader userInputReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return userInputReader.readLine();
        } catch (IOException e ) {
            e.printStackTrace();
        }
        return null;
    }

    public String receiveData() {
        try {
            String echo = input.readLine();
            Utils.print("Echo: " + echo);
            return echo;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void sendData(String data) {
        output.println(data);
    }

    public void close() throws IOException {
        input.close();
        output.close();
        socket.close();
    }

    private void createWriter() throws IOException {
        output = new PrintWriter(socket.getOutputStream(), true);
    }

    private void createReader() throws IOException {
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }
}
