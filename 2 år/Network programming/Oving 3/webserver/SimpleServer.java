package webserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class SimpleServer {
        private final static int PORTNR = 80;

        public static void main(String[] args) throws IOException {
            ServerSocket listener = new ServerSocket(PORTNR);
            System.out.println("Server kjører på " + PORTNR);


            while(true){
                Socket socket = listener.accept();
                System.err.println("Connected!");

                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                ArrayList<String> lines= new ArrayList<>();

                for (String line; (line = reader.readLine()) != null;) {
                    if (line.isEmpty()) break; // Stop when headers are completed. We're not interested in all the HTML.
                    lines.add(line);
                }

                String list = "";
                for(int i = 0; i < lines.size(); i++){
                    list += "<LI> " + lines.get(i) + "</LI>\n";
                }

                OutputStream outputStream = socket.getOutputStream();
                outputStream.write(("HTTP/1.0 200 OK \n" +
                        "Content-Type: text/html; charset=utf-8 \n" +
                        "\n" +
                        "<HTML><BODY>\n" +
                        "<H1> Hilsen. Du har koblet deg opp til min enkle web-tjener </h1>\n" +
                        "Header fra klient er:\n" +
                        "<UL>\n" +
                        list +
                        "</UL>\n" +
                        "</BODY></HTML>\n").getBytes());
                outputStream.flush();
                outputStream.close();

            }
        }

}
