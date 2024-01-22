import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Shroom shroom = new Shroom("K", "K", true);
        MushroomRegister  mushR = new MushroomRegister();
        Client client = new Client();
        while(true){
            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    mushR.registerShroom(shroom);
                    client.printArray(mushR.getShrooms());

            }
    }

    }

    /**
     * for printing every shroom object in shrooms array
     * @param shrooms
     */
    private void printArray(Shroom[] shrooms){
        for (Shroom s: shrooms){
            System.out.println(s);
        }
    }


}
