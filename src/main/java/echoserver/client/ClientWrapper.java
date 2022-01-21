package echoserver.client;

import java.io.IOException;
import java.net.InetAddress;

public interface ClientWrapper {
    void startClientSocket(InetAddress host, int port) throws IOException;
    String getUserInput() throws IOException;
    String receiveData();
    void sendData(String data);
    void close() throws IOException;
}
