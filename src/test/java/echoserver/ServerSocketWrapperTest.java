package echoserver;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.*;
import static org.testng.AssertJUnit.assertTrue;

class ServerSocketWrapperTest {

    @Test
    void startServerSocket() throws IOException {
        int port = 8080;
        ServerSocketWrapperSpy socketWrapperSpy = new ServerSocketWrapperSpy();
        socketWrapperSpy.startServerSocket(8080);
        assertTrue(socketWrapperSpy.wasServerSocketStarted());
    }
}