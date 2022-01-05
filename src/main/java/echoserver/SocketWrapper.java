package echoserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;

public interface SocketWrapper {
    ServerSocket startServerSocket(int port) throws IOException;
    Socket connectToClient(ServerSocket socket) throws IOException;
    static void close() {}
}
