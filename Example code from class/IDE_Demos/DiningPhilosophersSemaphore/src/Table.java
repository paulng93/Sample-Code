import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Steve on 2/22/14.
 */
public class Table {

    private static int NumPhilosophers = 50; // default to 5

    private static Chopstick chopsticks[] = null;

    public static void main(String[] args) {
        ExecutorService application = Executors.newCachedThreadPool();

        if(args.length >= 1) {
            NumPhilosophers = Integer.parseInt(args[0]);
        }

//      SynchronizedBuffer sharedLocation = new SynchronizedBuffer();

        // Initialize the chopstick array object
        Chopstick chopsticks = new Chopstick(NumPhilosophers);


        System.out.println("Dining Philosophers Algorithm");
        for(int i=1; i <= NumPhilosophers; i++) {
            // determine which chopsticks are positioned with each philosopher
            // by convention the right chopstick will equal the philosophers number
            application.execute(new Philosopher(i, chopsticks));
        }
        application.shutdown();
    }
}

