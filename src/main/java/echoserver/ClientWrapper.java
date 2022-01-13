package echoserver;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public interface ClientWrapper {
    Socket startClientSocket(InetAddress host, int port) throws IOException;
    String getUserInput() throws IOException;
    String receiveData();
    void sendData(String data);
    void close();
}
