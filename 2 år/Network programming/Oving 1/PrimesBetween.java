import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class PrimesBetween {

    public static void main(String[] args) throws InterruptedException {
        // Input numbers
        Scanner sc = new Scanner(System.in);

        System.out.println("First number");
        int start = sc.nextInt();
        System.out.println("Last number");
        int end = sc.nextInt();
        System.out.println("nr of threads");
        int numThreads = sc.nextInt();
        long startTime = System.currentTimeMillis();

        // Create a thread pool
        Thread[] threads = new Thread[numThreads];

        // Divide the range into equal chunks for each thread
        int chunkSize = (end - start) / numThreads;
        int startIndex = start;
        int endIndex = startIndex + chunkSize;

        List<Integer> primeNumbers = Collections.synchronizedList(new ArrayList<>());
        // Create and start the threads
        for (int i = 0; i < numThreads; i++) {
            PrimeNumberThread thread = new PrimeNumberThread(startIndex, endIndex, primeNumbers);
            threads[i] = new Thread(thread);
            threads[i].start();
            startIndex = endIndex;
            endIndex += chunkSize;
        }

        // Wait for all the threads to finish
        for (int i = 0; i < numThreads; i++) {
            threads[i].join();
        }

        // Sort the list of prime numbers
        Collections.sort(primeNumbers);

        // Print the prime numbers
        System.out.println("Prime numbers between " + start + " and " + end + ":");
        for (Integer primeNumber : primeNumbers) {
            System.out.println(primeNumber);
        }
        System.out.println("Time elapsed: "+ (System.currentTimeMillis() - startTime) +"ms");
    }
}

class PrimeNumberThread implements Runnable {
    private int start;
    private int end;
    private List<Integer> primeNumbers;

    public PrimeNumberThread(int start, int end, List<Integer> primeNumbers) {
        this.start = start;
        this.end = end;
        this.primeNumbers = primeNumbers;
    }

    @Override
    public void run() {
        for (int i = start; i < end; i++) {
            if (isPrime(i)) {
                primeNumbers.add(i);
            }
        }
    }

    private boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

}
