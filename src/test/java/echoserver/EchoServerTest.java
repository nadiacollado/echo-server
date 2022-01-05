package echoserver;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class EchoServerTest {
    @Mock
    ServerSocket serverSocket;
    @Mock
    Socket clientSocket;

    @Test
    void main() {
    }

//    @Test
//    public void testServerSocketIsCreated() throws IOException {
//        assertNotNull(EchoServer.startServerSocket(8080));
//    }
//
//    @Test
//    public void testServerSocketIsCreatedWithSpecifiedPort() throws IOException {
//        int testPort = 7777;
//        ServerSocket testServerSocket = EchoServer.startServerSocket(testPort);
//        assertEquals(testServerSocket.getLocalPort(), testPort);
//    }
//
//    @Test
//    public void testClientSocketIsCreated() throws IOException {
//        when(serverSocket.accept()).thenReturn(new Socket());
//        assertNotNull(EchoServer.startClientSocket(serverSocket));
//    }

}