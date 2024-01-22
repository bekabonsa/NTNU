import java.util.Date;

public class Exponents {
    public static void main(String[] args) {

        double result1;
        double result2;
        double result3;
        double result4;

        Date start = new Date();
        int runder = 0;
        double tid;
        Date slutt;
        do {
            result1 = tweakedExponentiation(1.001,100000);
            slutt = new Date();
            ++runder;
        } while (slutt.getTime()-start.getTime() < 1000);
        tid = (double) (slutt.getTime()-start.getTime()) / runder;

        System.out.println(result1 +", time elapsed: " + tid);
    }

    public static double exponentiation(double x, int n){
        if(n==0) return 1;
        return x * exponentiation(x, n-1);
    }

    public static double tweakedExponentiation(double x, int n){
        if(n==0) return 1;
        else if(n%2!=0){
            return x*tweakedExponentiation(x*x, (n-1)/2);
        }
        else return tweakedExponentiation(x*x, n/2);
    }

}

class nativePower{
    public static void main(String[] args) {
        double result1;
        Date start = new Date();
        int runder = 0;
        double tid;
        Date slutt;
        do {
            result1 = Math.pow(1.001,2000);
            slutt = new Date();
            ++runder;
        } while (slutt.getTime()-start.getTime() < 1000);
        tid = (double) (slutt.getTime()-start.getTime()) / runder;

        System.out.println(result1+", time elapsed: " + tid);
    }

}