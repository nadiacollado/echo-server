package echoserver;
import java.io.*;
import java.net.*;

public class EchoClient {
    int port;
    InetAddress host;
    ClientWrapper socketWrapper;

    public EchoClient(int port, InetAddress host, ClientWrapper socketWrapper) {
        this.port = port;
        this.host = host;
        this.socketWrapper = socketWrapper;
    }

    public void startEchoClient() {
        String clientData;
        try {
            socketWrapper.startClientSocket(host, port);
            while (true) {
                while((clientData = socketWrapper.getUserInput()) != null) {
                    socketWrapper.sendData(clientData);
                    socketWrapper.receiveData();
                    if (Utils.quit(clientData)) {
                        socketWrapper.close();
                    }
                }
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + host);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + host);
            System.exit(1);
        }
    }
}

