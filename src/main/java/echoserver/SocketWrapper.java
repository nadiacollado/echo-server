package echoserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;

public interface SocketWrapper {
    ServerSocket startServerSocket(int port) throws IOException;
    Socket startClientSocket(ServerSocket socket) throws IOException;
    static void manageIOStream(PrintWriter output, BufferedReader input) throws IOException {}
    static void close() {}
}
