package echoserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketWrapperSpy implements SocketWrapper {
    public BufferedReader input;
    public StringWriter output;
    public String sentData;
    public int givenPort;
    public boolean closeCalled = false;
    public boolean startServerSocketCalled = false;
    public boolean connectToClientCalled = false;

    public ServerSocketWrapperSpy(BufferedReader input, StringWriter output) {
        this.input = input;
        this.output = output;
    }

    public ServerSocketWrapperSpy(){}

    public ServerSocket startServerSocket(int port) throws IOException {
        givenPort = port;
        startServerSocketCalled = true;
        return null;
    }

    public int getPort() {
        return givenPort;
    }

    public boolean wasServerSocketStarted() {
        return startServerSocketCalled;
    }

    public Socket connectToClient(ServerSocket socket) throws IOException {
        connectToClientCalled = true;
        return null;
    }

    public boolean wasServerSocketConnectedToClient() {
        return connectToClientCalled;
    }

    public String receiveData() {
        try {
            return input.readLine();
        } catch (IOException e) {
            System.err.println("Error");
        }
        return null;
    }

    public void sendData(String data) {
        output.write(data);
        sentData = output.toString();
    }

    public boolean quit(String keyword) {
        return true;
    }

    public String getSentData() {
        return sentData;
    }

    public void close() {
        closeCalled = true;
    }
}
