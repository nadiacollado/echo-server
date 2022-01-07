package echoserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientSocketWrapper {
    private static BufferedReader input;
    private static PrintWriter output;
    private static Socket clientSocket;

    public Socket startClientSocket(int port, String host) throws IOException {
        try {
            clientSocket = new Socket(host, port);
            System.out.println("Client socket created");
            buildIOStream();
        } catch (IOException e) {
            System.out.println("Client socket not created");
        }
        return clientSocket;
    }

    public static void buildIOStream() throws IOException {
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

    public static PrintWriter createSocketWriter(Socket clientSocket) throws IOException {
        output = new PrintWriter(clientSocket.getOutputStream(), true);
        return output;
    }

    public static BufferedReader createSocketReader(Socket clientSocket) throws IOException {
        input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        return input;
    }

    public static String readFromInputStream() throws IOException {
        return input.readLine();
    }

    public static void writeToOutputStream(String data) {
        if (!quit(data)) {
            System.out.println("Client: " + data);
            output.println("Echo: " + data);
        }
    }

    public static boolean quit(String keyword) {
        keyword = keyword.toLowerCase();
        return (keyword.equals("q") || keyword.equals("quit"));
    }

    public static void close() throws IOException {
        input.close();
        output.close();
        clientSocket.close();
    }
}
