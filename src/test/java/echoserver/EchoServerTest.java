package echoserver;

import org.junit.jupiter.api.Test;
import java.io.*;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;


class EchoServerTest {
    @Test
    public void testServerSocketIsCreated() {
        PrintWriter output = new PrintWriter(new StringWriter(), true);
        BufferedReader input = new BufferedReader(new StringReader("test"));
        MockServerSocketWrapper mockSocketWrapper = new MockServerSocketWrapper(input, output);
        EchoServer echoServer = new EchoServer(8080, mockSocketWrapper);
        echoServer.startEchoServer();

        assertTrue(mockSocketWrapper.wasStartServerSocketCalled());
    }

    @Test
    public void testServerSocketIsCreatedWithCorrectPort() {
        int port = 7777;
        PrintWriter output = new PrintWriter(new StringWriter(), true);
        BufferedReader input = new BufferedReader(new StringReader("test"));
        MockServerSocketWrapper mockSocketWrapper = new MockServerSocketWrapper(input, output);
        EchoServer echoServer = new EchoServer(port, mockSocketWrapper);
        echoServer.startEchoServer();

        assertEquals(mockSocketWrapper.getPort(), port);
    }

    @Test
    public void testServerSocketConnectsToClient() {
        PrintWriter output = new PrintWriter(new StringWriter(), true);
        BufferedReader input = new BufferedReader(new StringReader("test"));
        MockServerSocketWrapper mockSocketWrapper = new MockServerSocketWrapper(input, output);
        EchoServer echoServer = new EchoServer(8080, mockSocketWrapper);
        echoServer.startEchoServer();

        assertTrue(mockSocketWrapper.wasConnectToClientCalled());
    }

    @Test
    public void testServerSocketEchoesBackInput() {
        PrintWriter output = new PrintWriter(new StringWriter(), true);
        BufferedReader input = new BufferedReader(new StringReader("test"));
        MockServerSocketWrapper mockSocketWrapper = new MockServerSocketWrapper(input, output);
        EchoServer echoServer = new EchoServer(8080, mockSocketWrapper);
        echoServer.startEchoServer();

        assertEquals("test", mockSocketWrapper.getSentData());
    }

    @Test
    public void testServerSocketClosesWhenGivenQuitAsInput() {
        PrintWriter output = new PrintWriter(new StringWriter(), true);
        BufferedReader input = new BufferedReader(new StringReader("quit"));
        MockServerSocketWrapper mockSocketWrapper = new MockServerSocketWrapper(input, output);
        EchoServer echoServer = new EchoServer(8080, mockSocketWrapper);
        echoServer.startEchoServer();

        assertTrue(mockSocketWrapper.wasCloseCalled());
    }

    @Test
    public void testServerSocketCloses() {
        PrintWriter output = new PrintWriter(new StringWriter(), true);
        BufferedReader input = new BufferedReader(new StringReader("check"));
        MockServerSocketWrapper mockSocketWrapper = new MockServerSocketWrapper(input, output);
        EchoServer echoServer = new EchoServer(8080, mockSocketWrapper);
        echoServer.startEchoServer();

        assertTrue(mockSocketWrapper.wasCloseCalled());
    }
}