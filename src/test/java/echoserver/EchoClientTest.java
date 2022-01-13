package echoserver;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;

import static org.junit.jupiter.api.Assertions.*;

class EchoClientTest {
    @Test
    public void testClientSocketIsCreated() throws UnknownHostException {
        InetAddress address = InetAddress.getLocalHost();
        PrintWriter output = new PrintWriter(new StringWriter(), true);
        BufferedReader input = new BufferedReader(new StringReader("test"));
        MockClientSocketWrapper mockClientWrapper = new MockClientSocketWrapper(input, output);

        EchoClient client = new EchoClient(8080, address, mockClientWrapper);
        client.startEchoClient();

        assertTrue(mockClientWrapper.wasStartClientSocketCalled());
    }

    @Test
    public void testClientSocketIsCreatedWithCorrectPort() throws UnknownHostException {
        int port = 7777;
        InetAddress host = InetAddress.getLocalHost();
        PrintWriter output = new PrintWriter(new StringWriter(), true);
        BufferedReader input = new BufferedReader(new StringReader("test"));
        MockClientSocketWrapper mockClientWrapper = new MockClientSocketWrapper(input, output);
        EchoClient client = new EchoClient(7777, host, mockClientWrapper);
        client.startEchoClient();

        assertEquals(mockClientWrapper.getPort(), port);
    }

    @Test
    public void testClientSocketIsCreatedWithCorrectHost() throws UnknownHostException {
        InetAddress host = InetAddress.getByName("127.0.0.1");
        PrintWriter output = new PrintWriter(new StringWriter(), true);
        BufferedReader input = new BufferedReader(new StringReader("test"));
        MockClientSocketWrapper mockClientWrapper = new MockClientSocketWrapper(input, output);
        EchoClient client = new EchoClient(8080, host, mockClientWrapper);
        client.startEchoClient();

        assertEquals(mockClientWrapper.getHost(), host);
    }

    @Test
    public void testClientSocketReturnsClientInput() throws IOException {
        InetAddress host = InetAddress.getByName("127.0.0.1");
        PrintWriter output = new PrintWriter(new StringWriter(), true);
        BufferedReader input = new BufferedReader(new StringReader("test"));
        MockClientSocketWrapper mockClientWrapper = new MockClientSocketWrapper(input, output);
        EchoClient client = new EchoClient(8080, host, mockClientWrapper);
        client.startEchoClient();

        assertEquals(mockClientWrapper.receiveData(), "test");
    }
}