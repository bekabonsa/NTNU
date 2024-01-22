package calculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread implements Runnable{
    private final Socket connection;

    // Constructor
    public ServerThread(Socket connection)
    {
        this.connection = connection;
    }

    public void run() {
        InputStreamReader readConnection;
        BufferedReader reader = null;
        PrintWriter writer = null;

        try {
            /* Open stream for communication with client program */
            readConnection = new InputStreamReader(connection.getInputStream());
            reader = new BufferedReader(readConnection);
            writer = new PrintWriter(connection.getOutputStream(), true);

            writer.println("Welcome to my test server!");
            writer.println("Try out the simple calculator below");

            /* receives data from the client */
            String oneLiner = reader.readLine();
            Double result = 0.0d;
            while (oneLiner != null) {

                double num1 = 0.0d;
                double num2 = 0.0d;
                try{
                    num1 = Double.parseDouble(oneLiner);
                    oneLiner = reader.readLine();
                    num2 = Double.parseDouble(oneLiner);
                    oneLiner = reader.readLine();
                }catch(Exception e){
                    writer.println("Error parsing the numbers");
                    e.printStackTrace();
                }

                String operator = oneLiner;

                if (oneLiner.equals("+")) result = num1 + num2;
                else if(oneLiner.equals("-")) result = num1 - num2;
                else{
                    writer.println("valid operators are + and -");
                    return;
                }
                writer.println("The answer to " + num1 + operator + num2 + " = " + result);
                oneLiner = reader.readLine();
            }

            /* close connections*/
            System.out.println("Server shutting down!!");
            reader.close();
            writer.close();
            connection.close();

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            try {
                if (writer != null) {
                    writer.println("Error caught! restart the program.");
                    writer.close();
                }
                if (reader != null) {
                    reader.close();
                    connection.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }
}
