

public class Oppgave2 {
    public static void main(String[] args){
      
        MinRandom rand1 = new MinRandom();

        for(int i = 0; i<20; i++){
            System.out.println("Decimals "+rand1.nextDecimal(5.0, 15.0));
        }
        
        for(int i = 0; i<20; i++){
            System.out.println("Integers "+rand1.nextInt(5, 15));
        }

    }
}
