package echoserver;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class ClientSocketWrapper implements ClientWrapper {
    private BufferedReader input;
    private PrintWriter output;
    private Socket socket;

    public Socket startClientSocket(InetAddress host, int port) {
        try {
            socket = new Socket(host, port);
            System.out.println("Connection successful using " + host + " on port " + port);
            buildIOStream();
        } catch (IOException e) {
            System.out.println("Client socket not created");
        }
        return socket;
    }

    public void buildIOStream() throws IOException {
        createWriter(socket);
        createReader(socket);
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
            String echo = readFromInputStream();
            System.out.println("Echo: " + echo);
            return echo;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void sendData(String data) {
        writeToOutputStream(data);
    }

    private PrintWriter createWriter(Socket socket) throws IOException {
        output = new PrintWriter(socket.getOutputStream(), true);
        return output;
    }

    private BufferedReader createReader(Socket socket) throws IOException {
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        return input;
    }

    private String readFromInputStream() throws IOException {
        return input.readLine();
    }

    private void writeToOutputStream(String data) {
        output.println("Client message: " + data);
    }

    public void close() {
        try {
            input.close();
            output.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
