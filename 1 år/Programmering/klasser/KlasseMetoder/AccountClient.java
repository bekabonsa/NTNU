package TestPackage;
import static javax.swing.JOptionPane.*;
import java.util.Scanner;

public class AccountClient{

    static AccountTest jeff = new AccountTest(20, "Jeff", 1e6);

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        String ok = in.nextLine();

        System.out.println(jeff);
    }


}