import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Semaphore;

/**
 * Created by Steve on 10/19/14.
 */
public class SemaphoreFactoryExec {
    public static void main(String args[]) {
        Semaphore sem = new Semaphore(1);
        Thread[] bees = new Thread[5];

        System.out.println("Starting Executor");
        ExecutorService threadExecutor = Executors.newCachedThreadPool();
        for(int i=0; i < 5; i++) {
            bees[i] = new Thread(new Worker(sem, "BEE"+i));
            threadExecutor.execute(bees[i]);
        }
        System.out.println("Threads Initialized");

        // kick off thread execution under executor control
        threadExecutor.shutdown();
        System.out.println("Threads Started");
    }
}
