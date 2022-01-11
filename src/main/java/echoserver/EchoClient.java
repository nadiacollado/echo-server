package echoserver;
import java.io.*;
import java.net.*;

public class EchoClient {
    private static int port = 8080;
    private static String host;
    private static Socket clientSocket;

    public static void main(String[] args) {
        host = args[0];
        port = Integer.parseInt(args[1]);

        if (args.length != 2) {
            System.err.println("Usage: java EchoClient <host name> <port number>");
            System.exit(1);
        }

        try {
            ClientSocketWrapper clientSocketWrapper = new ClientSocketWrapper();
            startEchoClient(clientSocketWrapper);
        } catch (IOException e) {
            System.out.println("Cannot create client socket");
        }
    }

    public static void startEchoClient(ClientSocketWrapper clientSocketWrapper) throws IOException {
        try {
            clientSocket = clientSocketWrapper.startClientSocket(port, host);
            System.out.println("Connection successful using host " + host + " on " + port);
            String clientData;

            while ((clientData = clientSocketWrapper.receiveData()) != null) {
                clientSocketWrapper.sendData(clientData);

                if (clientSocketWrapper.quit(clientData)) {
                    clientSocketWrapper.close();
                }
            }
            clientSocketWrapper.close();
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + host);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + host);
            System.exit(1);
        }
    }
}

