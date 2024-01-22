import java.util.Random;

public class ArrayManager {

    public static int[] generateArray(int n) {
        int[] t = new int[n];
        Random random = new Random();
        for (int i = 0; i < t.length; i++) {
            t[i] = random.nextInt(100);
        }
        return t;
    }

    public static int[] generateSortedArray(int n){
        int[] array = new int[n];
        for(int i = 0; i < n; i++){
            array[i] = i;
        }
        return array;
    }

    public static int[] generateArrayWithDuplicates(int n){
        int[] array = new int[n];
        //divide into array into 5
        Random r = new Random();
        for(int i = 0; i<n; i++){
            array[i] = r.nextInt(3);
        }
       return array;
    }
    public static int checkSum(int[] t){
        int sum = 0;
        for (int i = 0; i < t.length; i++){
            sum += t[i];
        }
        return sum;
    }

    public static void swap(int[] t, int i, int j) {
        int k = t[j];
        t[j] = t[i];
        t[i] = k;
    }

    public static boolean isSorted(int[] t){
        for(int i = 0; i < t.length -2; i++){
            if(t[i+1]<t[i]) return false;
        }
        return true;
    }




}
