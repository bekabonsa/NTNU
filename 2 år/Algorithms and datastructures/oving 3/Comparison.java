import java.util.Arrays;

//comparing the two algorithms
public class Comparison {
    public static int n = 10000000;
    public static void main(String[] args) {
        //generate array with random numbers between 0 and 100
        int[] t = ArrayManager.generateArray(n);

        //check the sum before sorting
        int startingSum = ArrayManager.checkSum(t);
        long timerStart = System.nanoTime();
        QuickSort.quicksort(t, 0, t.length-1);
        long timerEnd = System.nanoTime();
        boolean sumCheck = (startingSum == ArrayManager.checkSum(t));
        System.out.println("---------------***-------------\n");
        System.out.println("Used time on quicksort with random numbers: " +((timerEnd-timerStart)/1000000)+ " ms"+
                ". Is array sorted = "+ ArrayManager.isSorted(t)+
                ". Is checksum the same after sorting = " + sumCheck +"\n");


        //-------------------------------****-------------------------------


        int[] sortedArray = ArrayManager.generateSortedArray(n);
        startingSum = ArrayManager.checkSum(sortedArray);
        timerStart = System.nanoTime();
        QuickSort.quicksort(sortedArray, 0, n-1);
        timerEnd = System.nanoTime();
        sumCheck = (startingSum == ArrayManager.checkSum(sortedArray));

        System.out.println("---------------***-------------\n");
        System.out.println("Used time on quicksort with sorted array: " +((timerEnd-timerStart)/1000000)+ " ms"+
                ". Is array sorted = "+ ArrayManager.isSorted(sortedArray)+
                ". Is checksum the same after sorting = " + sumCheck +"\n");


        //-------------------------------****-------------------------------

        int[] duplicateArray = ArrayManager.generateArrayWithDuplicates(n);
        startingSum = ArrayManager.checkSum(duplicateArray);
        timerStart = System.nanoTime();
        QuickSort.quicksort(duplicateArray, 0, n-1);
        timerEnd = System.nanoTime();
        sumCheck = (startingSum == ArrayManager.checkSum(duplicateArray));

        System.out.println("---------------***-------------\n");
        System.out.println("Used time on quicksort with lots of duplicates in array: " +((timerEnd-timerStart)/1000000)+
                " ms"+ ". Is array sorted = "+ ArrayManager.isSorted(duplicateArray)+
                ". Is checksum the same after sorting = " + sumCheck +"\n");

        //-------------------------------****-------------------------------

        int[] arr = ArrayManager.generateArray(n);

        //check the sum before sorting
        startingSum = ArrayManager.checkSum(arr);
        timerStart = System.nanoTime();
        DualSort.dualPivotQuickSort(arr, 0, t.length-1);
        timerEnd = System.nanoTime();
        sumCheck = (startingSum == ArrayManager.checkSum(arr));
        System.out.println("---------------***-------------***-----------------***-------------***\n");
        System.out.println("Used time on dual-sort with random numbers: " +((timerEnd-timerStart)/1000000)+ " ms"+
                ". Is array sorted = "+ ArrayManager.isSorted(arr)+
                ". Is checksum the same after sorting = " + sumCheck +"\n");

        //-------------------------------****-------------------------------

        arr = ArrayManager.generateSortedArray(n);;

        //check the sum before sorting
        startingSum = ArrayManager.checkSum(arr);
        timerStart = System.nanoTime();
        DualSort.dualPivotQuickSort(arr, 0, t.length-1);
        timerEnd = System.nanoTime();
        sumCheck = (startingSum == ArrayManager.checkSum(arr));
        System.out.println("---------------***-------------***-----------------***-------------***\n");
        System.out.println("Used time on dual-sort with sorted array: " +((timerEnd-timerStart)/1000000)+ " ms"+
                ". Is array sorted = "+ ArrayManager.isSorted(arr)+
                ". Is checksum the same after sorting = " + sumCheck +"\n");

        //-------------------------------****-------------------------------

        arr = ArrayManager.generateArrayWithDuplicates(n);

        //check the sum before sorting
        startingSum = ArrayManager.checkSum(arr);
        timerStart = System.nanoTime();
        DualSort.dualPivotQuickSort(arr, 0, t.length-1);
        timerEnd = System.nanoTime();
        sumCheck = (startingSum == ArrayManager.checkSum(arr));
        System.out.println("---------------***-------------***-----------------***-------------***\n");
        System.out.println("Used time on dual-sort with lots of duplicate numbers: " +((timerEnd-timerStart)/1000000)+ " ms"+
                ". Is array sorted = "+ ArrayManager.isSorted(arr)+
                ". Is checksum the same after sorting = " + sumCheck +"\n");

    }

}
