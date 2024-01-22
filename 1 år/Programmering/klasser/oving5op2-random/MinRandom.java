import java.util.Random;

public class MinRandom{

    java.util.Random Random = new java.util.Random();
    Random rand = new Random();

    public int nextInt(int start, int end){
        
        int diff = end-start;
        return this.rand.nextInt(diff+1)+start;

    }

    public double nextDecimal(double start, double end){

        double diff = end-start;
        double rand1 = rand.nextDouble();
        return rand1*diff+start;

    }

}