package echoserver;

import org.junit.jupiter.api.Test;
import java.io.*;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

class EchoServerTest {
    @Test
    public void testServerSocketIsCreated() throws IOException {
        StringWriter output = new StringWriter();
        BufferedReader input = new BufferedReader(new StringReader("test"));
        ServerSocketWrapperSpy socketWrapperSpy = new ServerSocketWrapperSpy(input, output);
        EchoServer echoServer = new EchoServer(socketWrapperSpy);
        echoServer.startEchoServer(socketWrapperSpy);

        assertTrue(socketWrapperSpy.wasServerSocketStarted());
    }

    @Test
    public void testServerSocketIsCreatedWithCorrectPort() throws IOException {
        int port = 7777;
        StringWriter output = new StringWriter();
        BufferedReader input = new BufferedReader(new StringReader("test"));
        ServerSocketWrapperSpy socketWrapperSpy = new ServerSocketWrapperSpy(input, output);
        EchoServer echoServer = new EchoServer(port, socketWrapperSpy);
        echoServer.startEchoServer(socketWrapperSpy);

        assertEquals(socketWrapperSpy.getPort(), port);
    }

    @Test
    public void testServerSocketConnectsToClient() throws IOException {
        StringWriter output = new StringWriter();
        BufferedReader input = new BufferedReader(new StringReader("test"));
        ServerSocketWrapperSpy socketWrapperSpy = new ServerSocketWrapperSpy(input, output);
        EchoServer echoServer = new EchoServer(socketWrapperSpy);
        echoServer.startEchoServer(socketWrapperSpy);

        assertTrue(socketWrapperSpy.wasServerSocketConnectedToClient());
    }

    @Test
    public void testServerSocketReturnsInputAsOutput() throws IOException {
        StringWriter output = new StringWriter();
        BufferedReader input = new BufferedReader(new StringReader("test"));
        ServerSocketWrapperSpy socketWrapperSpy = new ServerSocketWrapperSpy(input, output);
        EchoServer echoServer = new EchoServer(socketWrapperSpy);
        echoServer.startEchoServer(socketWrapperSpy);

        assertEquals("test", socketWrapperSpy.getSentData());
    }
}