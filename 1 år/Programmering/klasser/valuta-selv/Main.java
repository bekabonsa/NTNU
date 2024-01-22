import javax.swing.*;



import java.util.Scanner;
import java.lang.Math;

public class Main {
    public static void main(String[] args){
        Valuta nok = new Valuta("NOK");
        nok.Nok();
        Valuta euro = new Valuta("EURO");
        euro.Euro();
        Valuta yen = new Valuta("YEN");
        yen.Yen();

        Scanner input1 = new Scanner(System.in);
        System.out.println("Would you like to convert to or from NOK? type 1 for conversion to NOK or 2 for conversion to other currencies");
        String choice= input1.nextLine();
        
    switch (choice) {
        case "1":
            System.out.println("conversion from ____ to nok. choose between USD, EURO and YEN.");
            double amountF = 0;
            String From = input1.nextLine();

            try{
            System.out.println("Type in the amount of "+From+" for conversion:");

                amountF = input1.nextDouble();

            } catch(Exception e){
                System.out.println("error: input numbers only");
            }

                        if(From.equals("USD")) System.out.println(amountF+" "+From+" to NOK is: "+amountF*Math.pow(nok.in_USD, -1));
                        if(From.equals("EURO")) System.out.println(amountF+" "+From+" to NOK is: "+amountF*Math.pow(nok.in_USD/euro.in_USD, -1));
                        if(From.equals("YEN")) System.out.println(amountF+" "+From+" to NOK is: "+amountF*Math.pow(nok.in_USD/yen.in_USD, -1));
                        break;
        case "2":
            System.out.println("From NOK to _____. choose between EURO, USD and YEN.");
                String ToCurrency = input1.nextLine();
            System.out.println("Type in the amount for conversion:");
                float Amount = 0f;
    try{
            String Amount1 = input1.nextLine();
            Amount = Float.parseFloat(Amount1);
    }catch(Exception e){
            System.out.println("Error: Only accepts numbers");
        }
            if (ToCurrency.equals("NOK"))System.out.println("NOK til NOK er NOK!");
            if(ToCurrency.equals("USD"))System.out.println(Amount+" NOK til USD er: "+Amount*nok.in_USD);
            if(ToCurrency.equals("EURO"))System.out.println(Amount+" NOK til EURO er: "+nok.in_USD/euro.in_USD*Amount);
            if(ToCurrency.equals("YEN"))System.out.println(Amount+" NOK til YEN er: "+nok.in_USD/yen.in_USD*Amount);
    }
    }

}
