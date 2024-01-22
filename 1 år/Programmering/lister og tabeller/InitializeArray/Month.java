package TestPackage;
import java.util.Arrays;
import java.util.Scanner;
public class Month {

    public static void main(String[] args){

        int[] month = new int[12];

        Arrays.fill(month, 30);
        month[1] = 28;

        System.out.println("Er det et skuddår?");
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();

        if(input.equals("Ja")||input.equals("ja"))  month[1] = 29;

        for(int x : month){
            System.out.println(x+"\n");
        }


    }

}
