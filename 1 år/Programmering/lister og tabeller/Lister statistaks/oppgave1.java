import java.lang.Math;
import java.util.Random;

public class oppgave1{

    public static void main(String[] args){

        java.util.Random random = new java.util.Random(); 
        int upto = 10;

        int repeat = 1000;
        int[] cases = new int[upto];

        

           

        for(int i = 0; i<repeat; i++){

            int rand =  random.nextInt(upto);
            cases[rand]++;         

        }

        for(int x = 0; x<cases.length; x++){

            double y = (double)(cases[x]);
            double z = (double)(repeat);
            double yzRatio = (double)(y/z);
            double amount1P = (double)(1f/100f*z);
            double starsA = (double) (yzRatio/amount1P);
            long starCount = Math.round(starsA);
            int sC = (int) starCount;
            //System.out.println(sC);
            
            String str = "*";
            System.out.println("Total "+(x+1)+"'s = "+cases[x]+" "+ str.repeat(sC));

        }

       // for(int x = 0; x<cases.size(); x++){  
            


      // }

      

    }

}

