import java.lang.IllegalArgumentException;

public class Fraction{
    private final double teller;
    private final double nevner;
    private static double result;

    public Fraction(double teller, double nevner){

        if(nevner == 0) throw new IllegalArgumentException("Value for nevner is 0. Cant divide by 0");

        else{

            double gcd = gcd(teller, nevner);       //kaller på funskjonen gcd som finner greatest common denominator
            this.teller = teller/gcd;               //deller med gcd for å forenkle teller
            this.nevner = nevner/gcd;               //----deler med gcd over og under brøk streken

        }

    }

    public Fraction(double teller){
        this.teller = teller;
        this.nevner = 1;
    }


    public static double gcd(double a, double b) {
        return b == 0 ? a : gcd(b, a % b);
    }


    public double getTeller(){
        return this.teller;
    }
    
    public double getNevner(){
        return this.nevner;
    }

    public static double getResult(){
        return result;
    }

   
    public static void subtract(Fraction a, Fraction b){
        result = (a.teller/a.nevner)-(b.teller/b.nevner);
    }

    public static void add(Fraction a, Fraction b){
        result = (a.teller/a.nevner)+(b.teller/b.nevner);
    }

    public static void multiply(Fraction a, Fraction b){
        result = (a.teller/a.nevner)*(b.teller/b.nevner);
    }

    public static void divide(Fraction a, Fraction b){
        result = (a.teller/a.nevner)/(b.teller/b.nevner);
    }

}