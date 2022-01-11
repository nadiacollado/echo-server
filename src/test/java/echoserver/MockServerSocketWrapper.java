package echoserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MockServerSocketWrapper implements SocketWrapper {
    public BufferedReader input;
    public StringWriter output;
    public String sentData;
    public int spyPort;
    public boolean closeCalled = false;
    public boolean startServerSocketCalled = false;
    public boolean connectToClientCalled = false;
    public boolean quitCalled = false;

    public MockServerSocketWrapper(BufferedReader input, StringWriter output) {
        this.input = input;
        this.output = output;
    }

    public ServerSocket startServerSocket(int port) {
        spyPort = port;
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
            System.err.println("Error");
        }
        return null;
    }

    public void sendData(String data) {
        output.write(data);
        sentData = output.toString();
    }

    public int getPort() {
        return spyPort;
    }

    public String getSentData() {
        return sentData;
    }

    public boolean quit(String keyword) {
        quitCalled = true;
        return true;
    }

    public void close() {
        closeCalled = true;
    }

    public boolean wasServerSocketStarted() {
        return startServerSocketCalled;
    }

    public boolean wasServerSocketConnectedToClient() {
        return connectToClientCalled;
    }

    public boolean wasQuitCalled() {
        return quitCalled;
    }

    public boolean wasServerSocketClosed() {
        return closeCalled;
    }
}
