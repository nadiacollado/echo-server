package echoserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

    @Override
    public Socket startClientSocket(ServerSocket socket) throws IOException {
        try {
            clientSocket = socket.accept();
            System.out.println("Connection successful");
        } catch (IOException e) {
            System.out.println("Connection unsuccessful");
        }
        return clientSocket;
    }

    public static void manageIOStream(PrintWriter output, BufferedReader input) throws IOException {
        String inputLine;
        while ((inputLine = readFromInputStream(input)) != null) {
            if (quit(inputLine)) {
                close();
                break;
            }
            System.out.println("Client input: " + inputLine);
            writeToOutputStream(output, inputLine);
        }
    }

    public static void close() throws IOException {
        input.close();
        output.close();
        clientSocket.close();
        serverSocket.close();
    }

    public static void startSocketIO(Socket clientSocket) throws IOException {
        manageIOStream(createSocketWriter(clientSocket), createSocketReader(clientSocket));
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
}
