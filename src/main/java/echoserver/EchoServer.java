package echoserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.ServerSocket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        int port = 8080;

        if (args.length >= 1) {
            port = Integer.parseInt(args[0]);
        } else if (args.length == 0){
            System.out.println("Port not provided. Using port 8080.");
        }

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.out.println("Issue trying to listen on port" + port);
            System.out.println(e.getMessage());
        }
        System.out.println("Listening for connection...");
        Socket clientSocket = serverSocket.accept();
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            System.out.println("Server has received the following input: " + inputLine);
            out.println(inputLine);
        }
    }
}
