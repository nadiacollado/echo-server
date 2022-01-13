package echoserver;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class MockClientSocketWrapper implements ClientWrapper {
    public BufferedReader input;
    public PrintWriter output;
    public String sentData;
    public int mockPort;
    public InetAddress mockHost;
    public boolean closeCalled = false;
    public boolean startClientSocketCalled = false;

    public MockClientSocketWrapper(BufferedReader input, PrintWriter output) {
        this.input = input;
        this.output = output;
    }

    public Socket startClientSocket(InetAddress host, int port) {
        mockPort = port;
        mockHost = host;
        startClientSocketCalled = true;
        return null;
    }

    public String getUserInput() {
        try {
            return input.readLine();
        } catch (IOException e){
            System.err.println("Error reading user input");
        }
        return null;
    }

    public String receiveData() {
        return sentData;
    }

    public void sendData(String data) {
        output.println(data);
        sentData = data;
    }

    public int getPort() {
        return mockPort;
    }

    public InetAddress getHost() {
        return mockHost;
    }

    public void close() {
        closeCalled = true;
    }

    public boolean wasStartClientSocketCalled() {
        return startClientSocketCalled;
    }

    public boolean wasCloseCalled() {
        return closeCalled;
    }
}
