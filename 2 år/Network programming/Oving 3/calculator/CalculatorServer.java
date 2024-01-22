package calculator;

import java.io.*;
import java.net.*;

public class CalculatorServer {
    public static void main(String[] args) throws IOException {
        final int PORTNR = 1234;
        ServerSocket server = null;
        try {
            server = new ServerSocket(PORTNR);
            // allow server to bind to same addr and port even if the previous server has not yet released the socket
            server.setReuseAddress(true);
            System.out.println("Initializing on port: " + PORTNR);

            while (true) {
                Socket connection = server.accept(); //waits for connections
                // Displaying that new client is connected to server
                System.out.println("New client connected: " + connection.getInetAddress().getHostAddress());
                //create a new thread
                ServerThread clientSock = new ServerThread(connection);

                //separately handle the client
                new Thread(clientSock).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (server != null) {
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}