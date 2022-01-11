package echoserver;

import org.junit.jupiter.api.Test;
import java.io.*;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;


class EchoServerTest {
    @Test
    public void testServerSocketIsCreated() {
        StringWriter output = new StringWriter();
        BufferedReader input = new BufferedReader(new StringReader("test"));
        MockServerSocketWrapper mockSocketWrapper = new MockServerSocketWrapper(input, output);
        EchoServer echoServer = new EchoServer(8080, mockSocketWrapper);
        echoServer.startEchoServer();

        assertTrue(mockSocketWrapper.wasServerSocketStarted());
    }

    @Test
    public void testServerSocketIsCreatedWithCorrectPort() {
        int port = 7777;
        StringWriter output = new StringWriter();
        BufferedReader input = new BufferedReader(new StringReader("test"));
        MockServerSocketWrapper mockSocketWrapper = new MockServerSocketWrapper(input, output);
        EchoServer echoServer = new EchoServer(port, mockSocketWrapper);
        echoServer.startEchoServer();

        assertEquals(mockSocketWrapper.getPort(), port);
    }

    @Test
    public void testServerSocketConnectsToClient() {
        StringWriter output = new StringWriter();
        BufferedReader input = new BufferedReader(new StringReader("test"));
        MockServerSocketWrapper mockSocketWrapper = new MockServerSocketWrapper(input, output);
        EchoServer echoServer = new EchoServer(8080, mockSocketWrapper);
        echoServer.startEchoServer();

        assertTrue(mockSocketWrapper.wasServerSocketConnectedToClient());
    }

    @Test
    public void testServerSocketEchoesBackInput() {
        StringWriter output = new StringWriter();
        BufferedReader input = new BufferedReader(new StringReader("test"));
        MockServerSocketWrapper mockSocketWrapper = new MockServerSocketWrapper(input, output);
        EchoServer echoServer = new EchoServer(8080, mockSocketWrapper);
        echoServer.startEchoServer();

        assertEquals("test", mockSocketWrapper.getSentData());
    }

    @Test
    public void testServerSocketQuitsWithCorrectInput() {
        StringWriter output = new StringWriter();
        BufferedReader input = new BufferedReader(new StringReader("quit"));
        MockServerSocketWrapper mockSocketWrapper = new MockServerSocketWrapper(input, output);
        EchoServer echoServer = new EchoServer(8080, mockSocketWrapper);
        echoServer.startEchoServer();

        assertTrue(mockSocketWrapper.wasQuitCalled());
    }

    @Test
    public void testServerSocketClosesWhenGivenQuitAsInput() {
        StringWriter output = new StringWriter();
        BufferedReader input = new BufferedReader(new StringReader("quit"));
        MockServerSocketWrapper mockSocketWrapper = new MockServerSocketWrapper(input, output);
        EchoServer echoServer = new EchoServer(8080, mockSocketWrapper);
        echoServer.startEchoServer();

        assertTrue(mockSocketWrapper.wasServerSocketClosed());
    }

    @Test
    public void testServerSocketCloses() {
        StringWriter output = new StringWriter();
        BufferedReader input = new BufferedReader(new StringReader("check"));
        MockServerSocketWrapper mockSocketWrapper = new MockServerSocketWrapper(input, output);
        EchoServer echoServer = new EchoServer(8080, mockSocketWrapper);
        echoServer.startEchoServer();

        assertTrue(mockSocketWrapper.wasServerSocketClosed());
    }
}