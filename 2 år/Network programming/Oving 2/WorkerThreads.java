
public class WorkerThreads {
    public static void main(String[] args) {
        Workers w = new Workers(4, 1);
        w.start();
        w.join();
    }

}
