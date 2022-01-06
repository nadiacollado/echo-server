package echoserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MockServerSocketWrapper implements SocketWrapper {
    boolean isClosed = false;

    public ServerSocket startServerSocket(int port) throws IOException {
        return null;
    }

    public Socket connectToClient(ServerSocket socket) throws IOException {
        return null;
    }

    public String receiveData() {
        return null;
    }

    public void sendData(String data) {

    }

    public void close() {
        isClosed = true;
    }
}
