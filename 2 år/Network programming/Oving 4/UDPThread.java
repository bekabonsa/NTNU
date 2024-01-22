import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

public class UDPThread {
    DatagramSocket socket;
    public UDPThread(DatagramSocket socket) throws IOException {
        this.socket = socket;
    }

    public void run(){
        try{
            DatagramPacket packetReceived;
            DatagramPacket packetSend;
            byte[] bytes;
            while (true){
                bytes = new byte[65535];
                System.out.println("Waiting for packet...");
                packetReceived = new DatagramPacket(bytes, bytes.length);
                socket.receive(packetReceived);
                String input = new String(bytes, 0, bytes.length);

                System.out.println("Packet received: " + input.trim());
                if (input.trim().equals("EXIT")){
                    break;
                }
                String result = Double.toString(calculateExpression(input));
                System.out.println("Calculation of: " + input.trim() + ", gives: " + result);
                byte[] bytesSend = result.getBytes(StandardCharsets.UTF_8);
                packetSend = new DatagramPacket(bytesSend, bytesSend.length, packetReceived.getAddress(), packetReceived.getPort());
                System.out.println("Sending calculation.");
                socket.send(packetSend);
                System.out.println("Calculation sent.");
                System.out.println("-----------------");
            }
            System.out.println("EXITING");
        } catch (IOException exception){
            System.out.println(exception.getMessage());
        }
    }

    public static double calculateExpression(String equation){
        String[] values = equation.split(" ");

        double numberOne = Double.parseDouble(values[0]);
        double numberTwo = Double.parseDouble(values[2]);
        if (values[1].equals("+")){
            return numberOne + numberTwo;
        } else if (values[1].equals("-")) {
            return numberOne - numberTwo;
        } else if (values[1].equals("*")) {
            return numberOne * numberTwo;
        } else {
            return numberOne / numberTwo;
        }
    }
}