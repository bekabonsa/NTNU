import java.util.concurrent.locks.*;

public class Workers {
    private final Lock lock = new ReentrantLock();
    private final Condition workerThreadsCondition = lock.newCondition();
    private final Condition eventLoopCondition = lock.newCondition();

    private int numWorkerThreads;
    private int numEventLoopThreads;

    private int workerThreadsStarted = 0;
    private int eventLoopStarted = 0;
    private int eventLoopTaskCounter = 0;

    public Workers(int numWorkerThreads, int numEventLoopThreads) {
        this.numWorkerThreads = numWorkerThreads;
        this.numEventLoopThreads = numEventLoopThreads;
    }

    public void start() {
        lock.lock();
        try {
            // Start worker threads
            for (int i = 0; i < numWorkerThreads; i++) {
                new Thread(() -> {
                    System.out.println("hi");
                    lock.lock();
                    try {
                        workerThreadsStarted++;
                        workerThreadsCondition.signal();
                    } finally {
                        lock.unlock();
                    }
                }).start();
            }

            // Wait for worker threads to start
            while (workerThreadsStarted < numWorkerThreads) {
                workerThreadsCondition.await();
            }

            // Start event loop
            for (int i = 0; i < numEventLoopThreads; i++) {
                new Thread(() -> {
                    lock.lock();
                    try {
                        while (eventLoopTaskCounter < 2) {
                            eventLoopTaskCounter++;
                            eventLoopStarted++;
                            eventLoopCondition.signal();
                        }
                    } finally {
                        lock.unlock();
                    }
                }).start();
            }

            // Wait for event loop to start
            while (eventLoopStarted < numEventLoopThreads) {
                eventLoopCondition.await();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void join() {
        lock.lock();
        try {
            // Wait for worker threads to complete
            while (workerThreadsStarted < numWorkerThreads) {
                workerThreadsCondition.await();
            }

            // Wait for event loop to complete
            while (eventLoopTaskCounter < 2) {
                eventLoopCondition.await();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}