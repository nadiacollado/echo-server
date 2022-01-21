package echoserver.server;

import java.io.IOException;
import java.net.*;

public interface SocketWrapper {
    ServerSocket startServerSocket(int port) throws IOException;
    Socket connectToClient(ServerSocket socket) throws IOException;
    String receiveData();
    void sendData(String data);
    void close() throws IOException;
}
