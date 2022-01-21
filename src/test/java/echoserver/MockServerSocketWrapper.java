package echoserver;

import echoserver.server.SocketWrapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MockServerSocketWrapper implements SocketWrapper {
    public BufferedReader input;
    public PrintWriter output;
    public String sentData;
    public int mockPort;
    public boolean closeCalled = false;
    public boolean startServerSocketCalled = false;
    public boolean connectToClientCalled = false;

    public MockServerSocketWrapper(BufferedReader input, PrintWriter output) {
        this.input = input;
        this.output = output;
    }

    public ServerSocket startServerSocket(int port) {
        mockPort = port;
        startServerSocketCalled = true;
        return null;
    }

    public Socket connectToClient(ServerSocket socket) {
        connectToClientCalled = true;
        return null;
    }

    public String receiveData() {
        try {
            return input.readLine();
        } catch (IOException e) {
            Utils.error("Error reading input", e);
        }
        return null;
    }

    public void sendData(String data) {
        output.write(data);
        sentData = data;
    }

    public int getPort() {
        return mockPort;
    }

    public String getSentData() {
        return sentData;
    }

    public void close() {
        closeCalled = true;
    }

    public boolean wasStartServerSocketCalled() {
        return startServerSocketCalled;
    }

    public boolean wasConnectToClientCalled() {
        return connectToClientCalled;
    }

    public boolean wasCloseCalled() {
        return closeCalled;
    }
}
