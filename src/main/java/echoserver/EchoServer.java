package echoserver;

import java.io.*;
import java.net.*;

public class EchoServer {
    private static int port = 8080;
    private static BufferedReader input;
    private static PrintWriter output;
    private static Socket clientSocket;
    private static ServerSocket serverSocket;

    public static void main(String[] args) {
        if (args.length >= 1) {
            port = Integer.parseInt(args[0]);
        } else {
            System.out.println("Port not provided. Using port 8080.");
        }

        try {
            serverSocket = startServerSocket(port);
            System.out.println("Listening for connection on port " + port);

            while (true) {
                Socket clientSocket = startClientSocket(serverSocket);
                startSocketIO(clientSocket);
            }
        } catch (IOException e) {
            System.out.println("Issue trying to listen on port " + port);
        }
    }

    public static void startSocketIO(Socket clientSocket) throws IOException {
        manageIOStream(createSocketWriter(clientSocket), createSocketReader(clientSocket));
    }

    public static ServerSocket startServerSocket(int port) throws IOException {
        return new ServerSocket(port);
    }

    public static Socket startClientSocket(ServerSocket socket) throws IOException {
        try {
            clientSocket = socket.accept();
            System.out.println("Connection successful");
        } catch (IOException e) {
            System.out.println("Connection unsuccessful");
        }
        return clientSocket;
    }

    public static PrintWriter createSocketWriter(Socket clientSocket) throws IOException {
        output = new PrintWriter(clientSocket.getOutputStream(), true);
        return output;
    }

    public static BufferedReader createSocketReader(Socket clientSocket) throws IOException {
        input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        return input;
    }

    public static void manageIOStream(PrintWriter out, BufferedReader input) throws IOException {
        String inputLine;
        while ((inputLine = readFromInputStream(input)) != null) {
            if (quit(inputLine)) {
                close();
                break;
            }
            System.out.println("Client input: " + inputLine);
            writeToOutputStream(out, inputLine);
        }
    }

    public static String readFromInputStream(BufferedReader input) throws IOException {
        return input.readLine();
    }

    public static void writeToOutputStream(PrintWriter out, String data) {
        out.println("Server output: " + data);
    }

    public static boolean quit(String keyword) {
        keyword = keyword.toLowerCase();
        return (keyword.equals("q") || keyword.equals("quit"));
    }

    public static void close() {
        try {
            input.close();
            output.close();
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
