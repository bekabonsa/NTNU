import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class HashTableDoubleHashing {
    static int[] arr;
    static int arrLength;
    static int totCollisions = 0;
    static double storedToSizeRatio = .75;
    static int addedNumbers = 0;


    public static void main(String[] args) {
        int size = 10000000;

        HashTableDoubleHashing hashtable = new HashTableDoubleHashing(size);
        int[] randInt = new int[size];
        Random rand = new Random();
        for (int i = 0; i < randInt.length; i++){
            randInt[i] = rand.nextInt((size*10))+1;
        }



        long start;
        long end;
        long time = 0;

        start = System.nanoTime();
        for(int i = 0; i < randInt.length; i++){
            hashtable.add(randInt[i]);
        }
        end = System.nanoTime();
        time = end - start;
        //System.out.println(Arrays.toString(arr));
        System.out.println("Time taken to fill my hash table: " + time/1000000 + "ms");
        System.out.println("total collisions: " + totCollisions);
        System.out.println("Load factor = total numbers/total storage: " + storedToSizeRatio);


        HashMap<Integer, Integer> hashMAP = new HashMap<Integer, Integer>();
        long start1;
        long end1;
        long time1 = 0;
        start1 = System.nanoTime();
        for(int i = 0; i < randInt.length; i++){
            hashMAP.put(i,randInt[i]);
        }
        end1 = System.nanoTime();
        time1 = end1-start1;
        System.out.println("Time taken by javas HashMap: " + time1/1000000 + "ms");
    }
    public HashTableDoubleHashing(int arrSize){
        arrLength = (int) ((float)arrSize/ storedToSizeRatio);
        this.arr = new int[arrLength];
    }

    public void add(int n){
        int index = hash1(n);

        while(arr[index] != 0){
            int index2 = hash2(n);
            //System.out.println(addedNumbers);
            //System.out.println(arr[index]);
            index = (index + index2) % arrLength;
            totCollisions++;
        }

        arr[index] = n;
        addedNumbers++;
    }

    public static int hash1(int n){
        //return n%arr.length;
        return n%arrLength;
    }


    public static int hash2(int n) throws IllegalArgumentException{
        //arrLength and output of hash2 must be relatively prime
        int output = 7-(n%7);
        if(output == arrLength || output == 0){
            throw new IllegalArgumentException("0 or arrlength");
        }
        return output;
    }

}
