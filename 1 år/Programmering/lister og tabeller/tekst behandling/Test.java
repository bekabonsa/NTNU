import java.sql.SQLOutput;
import java.util.Scanner;

public class Test {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Type in the text you want to modify: ");
        String mainText = input.nextLine();
        Textanalizer str = new Textanalizer(mainText);


        System.out.println("type in a word you want to replace. Inputs are case sensitive");
        String replaceMe = input.nextLine();
        System.out.println("type in a word you want to replace "+ replaceMe +" with:");
        String replacer = input.nextLine();

        System.out.println(replaceMe+" has been replaced with "+replacer+" Here are the results: ");
        System.out.println(str.replace(replaceMe,replacer));


        System.out.println(str.wordCount());
        System.out.println(str.upperCase());
        System.out.println(str.averagePeriodicWordCount());
        System.out.println(str.getText());
        System.out.println(str.averageWordLetterCount());
    }

}
