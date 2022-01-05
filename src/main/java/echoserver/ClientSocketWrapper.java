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
        } catch (IOException e) {
            System.out.println("Client socket not created");
        }
        return clientSocket;
    }

    public static void startSocketIO(Socket socket) throws IOException {
        clientSocket = socket;
        manageIOStream(createSocketWriter(socket), createSocketReader(socket));
    }

    public static void manageIOStream(PrintWriter output, BufferedReader input) throws IOException {
        String inputLine;
        while ((inputLine = readFromInputStream(input)) != null) {
            if (quit(inputLine)) {
                close();
                break;
            }
            System.out.println("Echo: " + inputLine);
            writeToOutputStream(output, inputLine);
        }
    }

    public static PrintWriter createSocketWriter(Socket clientSocket) throws IOException {
        output = new PrintWriter(clientSocket.getOutputStream(), true);
        return output;
    }

    public static BufferedReader createSocketReader(Socket clientSocket) throws IOException {
        input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        return input;
    }

    public static String readFromInputStream(BufferedReader input) throws IOException {
        return input.readLine();
    }

    public static void writeToOutputStream(PrintWriter output, String data) {
        output.println("Server output: " + data);
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
