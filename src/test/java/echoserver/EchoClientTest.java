package echoserver;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;

import static org.junit.jupiter.api.Assertions.*;

class EchoClientTest {
//    @Test
//    public void testClientSocketIsCreated() throws UnknownHostException {
//        InetAddress host = InetAddress.getByName("localhost");
//        PrintWriter output = new PrintWriter(new StringWriter(), true);
//        BufferedReader input = new BufferedReader(new StringReader("test"));
//        MockClientSocketWrapper mockClientWrapper = new MockClientSocketWrapper(input, output);
//
//        EchoClient client = new EchoClient(mockClientWrapper);
//        client.start(host, 8080);
//
//        assertTrue(mockClientWrapper.wasStartClientSocketCalled());
//    }
//
//    @Test
//    public void testClientSocketIsCreatedWithCorrectPort() throws UnknownHostException {
//        int port = 7777;
//        InetAddress host = InetAddress.getByName("localhost");
//        PrintWriter output = new PrintWriter(new StringWriter(), true);
//        BufferedReader input = new BufferedReader(new StringReader("test"));
//        MockClientSocketWrapper mockClientWrapper = new MockClientSocketWrapper(input, output);
//        EchoClient client = new EchoClient(mockClientWrapper);
//        client.start(host, port);
//
//        assertEquals(mockClientWrapper.getPort(), port);
//    }
//
//    @Test
//    public void testClientSocketIsCreatedWithCorrectHost() throws UnknownHostException {
//        InetAddress host = InetAddress.getLocalHost();
//        PrintWriter output = new PrintWriter(new StringWriter(), true);
//        BufferedReader input = new BufferedReader(new StringReader("test"));
//        MockClientSocketWrapper mockClientWrapper = new MockClientSocketWrapper(input, output);
//        EchoClient client = new EchoClient(mockClientWrapper);
//        client.start(host, 8080);
//
//        assertEquals(mockClientWrapper.getHost(), host);
//    }
//
//    @Test
//    public void testClientSocketReturnsClientInput() throws IOException {
//        InetAddress host = InetAddress.getByName("localhost");
//        PrintWriter output = new PrintWriter(new StringWriter(), true);
//        BufferedReader input = new BufferedReader(new StringReader("test"));
//        MockClientSocketWrapper mockClientWrapper = new MockClientSocketWrapper(input, output);
//        EchoClient client = new EchoClient(mockClientWrapper);
//        client.start(host, 8080);
//
//        assertEquals(mockClientWrapper.receiveData(), "test");
//    }
//
//    @Test
//    public void testClientSocketCloses() throws UnknownHostException {
//        InetAddress host = InetAddress.getByName("localhost");
//        PrintWriter output = new PrintWriter(new StringWriter(), true);
//        BufferedReader input = new BufferedReader(new StringReader("check"));
//        MockClientSocketWrapper mockClientWrapper = new MockClientSocketWrapper(input, output);
//        EchoClient client = new EchoClient(mockClientWrapper);
//        client.start(host, 8080);
//
//        assertTrue(mockClientWrapper.wasCloseCalled());
//    }
}