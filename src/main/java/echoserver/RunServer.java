package echoserver;

public class RunServer {
    public static void main(String[] args) {
        int port = 8080;

        if (args.length >= 1) {
            port = Integer.parseInt(args[0]);
        } else {
            System.out.println("Port not provided. Using port 8080.");
        }
        ServerSocketWrapper serverSocketWrapper = new ServerSocketWrapper();
        EchoServer server = new EchoServer(port, serverSocketWrapper);
        server.startEchoServer();
    }
}
