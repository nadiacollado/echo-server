package echoserver.client;

import java.io.IOException;
import java.net.InetAddress;

public class RunClient {
    public static void main(String[] args) throws IOException {
        InetAddress host = InetAddress.getByName("localhost");
        int port = 8080;

        if (args.length >= 2) {
            host = InetAddress.getByName(args[0]);
            port = Integer.parseInt(args[1]);
        }

        ClientSocketWrapper socketWrapper = new ClientSocketWrapper();
        EchoClient client = new EchoClient(socketWrapper);
        client.start(host, port);
    }
}
