package echoserver.client;
import echoserver.Utils;

import java.io.*;
import java.net.*;

public class EchoClient {
    ClientWrapper socketWrapper;

    public EchoClient(ClientWrapper socketWrapper) {
        this.socketWrapper = socketWrapper;
    }

    public void start(InetAddress host, int port) {
        try {
            socketWrapper.startClientSocket(host, port);
            String clientData;
            while((clientData = socketWrapper.getUserInput()) != null) {
                if (Utils.quit(clientData)) {
                    Utils.print("Closing connection");
                    socketWrapper.close();
                }
                socketWrapper.sendData(clientData);
                socketWrapper.receiveData();
            }
            socketWrapper.close();
        } catch (UnknownHostException e) {
            Utils.error(String.format("Don't know about host %s ", host), e);
        } catch (IOException e) {
            Utils.error(String.format("Couldn't get I/O for the connection to %s ", host), e);
        }
    }
}

