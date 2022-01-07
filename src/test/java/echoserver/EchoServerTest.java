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
        MockServerSocketWrapper mockSocketWrapper = new MockServerSocketWrapper(input, output);
        EchoServer echoServer = new EchoServer(mockSocketWrapper);
        echoServer.startEchoServer(mockSocketWrapper);

        assertTrue(mockSocketWrapper.wasServerSocketStarted());
    }

    @Test
    public void testServerSocketIsCreatedWithCorrectPort() throws IOException {
        int port = 7777;
        StringWriter output = new StringWriter();
        BufferedReader input = new BufferedReader(new StringReader("test"));
        MockServerSocketWrapper mockSocketWrapper = new MockServerSocketWrapper(input, output);
        EchoServer echoServer = new EchoServer(port, mockSocketWrapper);
        echoServer.startEchoServer(mockSocketWrapper);

        assertEquals(mockSocketWrapper.getPort(), port);
    }

    @Test
    public void testServerSocketConnectsToClient() throws IOException {
        StringWriter output = new StringWriter();
        BufferedReader input = new BufferedReader(new StringReader("test"));
        MockServerSocketWrapper mockSocketWrapper = new MockServerSocketWrapper(input, output);
        EchoServer echoServer = new EchoServer(mockSocketWrapper);
        echoServer.startEchoServer(mockSocketWrapper);

        assertTrue(mockSocketWrapper.wasServerSocketConnectedToClient());
    }

    @Test
    public void testServerSocketEchoesBackInput() throws IOException {
        StringWriter output = new StringWriter();
        BufferedReader input = new BufferedReader(new StringReader("test"));
        MockServerSocketWrapper mockSocketWrapper = new MockServerSocketWrapper(input, output);
        EchoServer echoServer = new EchoServer(mockSocketWrapper);
        echoServer.startEchoServer(mockSocketWrapper);

        assertEquals("test", mockSocketWrapper.getSentData());
    }

    @Test
    public void testServerSocketQuitsWithCorrectInput() throws IOException {
        StringWriter output = new StringWriter();
        BufferedReader input = new BufferedReader(new StringReader("quit"));
        MockServerSocketWrapper mockSocketWrapper = new MockServerSocketWrapper(input, output);
        EchoServer echoServer = new EchoServer(mockSocketWrapper);
        echoServer.startEchoServer(mockSocketWrapper);

        assertTrue(mockSocketWrapper.wasQuitCalled());
    }

    @Test
    public void testServerSocketClosesWhenGivenQuitAsInput() throws IOException {
        StringWriter output = new StringWriter();
        BufferedReader input = new BufferedReader(new StringReader("quit"));
        MockServerSocketWrapper mockSocketWrapper = new MockServerSocketWrapper(input, output);
        EchoServer echoServer = new EchoServer(mockSocketWrapper);
        echoServer.startEchoServer(mockSocketWrapper);

        assertTrue(mockSocketWrapper.wasServerSocketClosed());
    }

    @Test
    public void testServerSocketCloses() throws IOException {
        StringWriter output = new StringWriter();
        BufferedReader input = new BufferedReader(new StringReader("check"));
        MockServerSocketWrapper mockSocketWrapper = new MockServerSocketWrapper(input, output);
        EchoServer echoServer = new EchoServer(mockSocketWrapper);
        echoServer.startEchoServer(mockSocketWrapper);

        assertTrue(mockSocketWrapper.wasServerSocketClosed());
    }
}