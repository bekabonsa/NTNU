package calculator;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class CalculatorClient {
    public static void main(String[] args) throws IOException {
        final int PORTNR = 1234;

        /* use scanner to read input from user */
        Scanner readFromCMDL = new Scanner(System.in);
        System.out.println("Name of the machine on which the server is running on: ");
        String serverMachine = readFromCMDL.nextLine();

        /* Create a connection to the server */
        Socket connection = new Socket(serverMachine, PORTNR);
        System.out.println("Connection established :) Yippie!!");

        /* Create a connection to the server for communication */
        InputStreamReader readConnection = new InputStreamReader(connection.getInputStream());
        BufferedReader reader = new BufferedReader(readConnection);
        PrintWriter writer = new PrintWriter(connection.getOutputStream(), true);

        /* Read message from server and print to cmdL */
        String message1 = reader.readLine();
        String message2 = reader.readLine();
        System.out.println(message1+"\n" + message2+"\n");

        /* read input from user interaction */
        System.out.println("Press enter without any input to exit. to continue type any key");
        String oneLiner = readFromCMDL.nextLine();
        String response;
        while (!oneLiner.equals("")){

            System.out.println("Type in the first number");
            oneLiner = readFromCMDL.nextLine();
            writer.println(oneLiner);  //sends text to server
            System.out.println("Type in the second number");
            oneLiner = readFromCMDL.nextLine();
            writer.println(oneLiner);

            System.out.println("Type in the operator +/-");
            oneLiner = readFromCMDL.nextLine();
            writer.println(oneLiner);

            response = reader.readLine(); // gets a response from server
            System.out.println("Response from server ---> " + response);

        }

        /* close connections */
        reader.close();
        writer.close();
        connection.close();
    }
}