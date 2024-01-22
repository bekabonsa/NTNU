

public class Oppgave1 {
    
    public static void main(String[] args){
        Fraction a;
        Fraction b;

        try{

            a = new Fraction(32, 16);
            
          } 
          catch(IllegalArgumentException e)
          {

            System.out.println(e);
            a = new Fraction(1);

          }


          try{

            b = new Fraction(72, 46);

          }
           catch(IllegalArgumentException e)
          {

            System.out.println(e);
            b = new Fraction(1);

          }

          Fraction.add(a, b);
          System.out.println(Fraction.getResult());
          Fraction.subtract(a, b);
          System.out.println(Fraction.getResult());
          Fraction.multiply(a, b);
          System.out.println(Fraction.getResult());
          Fraction.divide(a, b);
          System.out.println(Fraction.getResult());
          System.out.println(b.getTeller() +"/" + b.getNevner()); 
          System.out.println(a.getTeller() +"/" + a.getNevner()); 
    }
}
