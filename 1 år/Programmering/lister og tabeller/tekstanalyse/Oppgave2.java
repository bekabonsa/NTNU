import java.util.Scanner;


public class Oppgave2{

    public static void main(String[] args){

        
        boolean Exit = false; 
        Scanner inputS = new Scanner(System.in);
        while(!Exit){
            System.out.println("Skriv inn tekst som skal analyseres. skriv inn Exit for å avslutte");
            String in = inputS.nextLine();
            
            if(in.equals("Exit"))break;

            Tekstanalyse text = new Tekstanalyse(in);

            System.out.println(text); 

            System.out.println("skriv inn bokstav for å finne antall treff");
            String in1 = inputS.nextLine();

            int antallTreff = text.letterOccurence(in1);
            System.out.println("Fant "+antallTreff+" av bokstaven "+in1);
            
            text.toString();



            try {Thread.sleep(1000);} catch (InterruptedException ie) {}

        }
        
       
    }

}
