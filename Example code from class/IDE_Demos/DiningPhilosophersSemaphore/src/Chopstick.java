import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Created by Steve Dame on 2/22/14.
 * Creates a class Chopstick that allows Dining Philosophers that are hungry to attempt to acquire so that they may
 * eat.
 */
public class Chopstick {
    /**
     * Track the number of chopsticks created during the table setting (e.g. equals number of philosophers)
     */
    private int NumChopsticks;
    /**
     * An array of semaphores, one for each philosopher, on which to block
     * if hungry and its forks are not both available.
     */
    private Semaphore[] self = null;
    /**
     * Mutual exclusion semaphore for read-update-write access to
     * philosopher state information to prevent deadlock conditions.
     */
    private Semaphore mutex = null;

    /**
     * Constructor.
     * @param numPhils The number of dining philosophers.
     */
    public Chopstick(int numPhils) {
        // initialize the number of chopsticks
        NumChopsticks = numPhils;
        // declare an array of Semaphores (one for each chopstick)
        self = new Semaphore[numPhils];
        // initialize all Semaphore "permits" to released state
        for (int i = 0; i < numPhils; i++) {
            self[i] = new Semaphore(1);
        }
        // initialize a mutex for the critical section of taking chopsticks
        mutex = new Semaphore(1);
    }

    /**
     * A hungry philosopher attempts to pick up his left and right chopsticks.
     * @param philo The number of the hungry philosopher.
     * @throws InterruptedException
     *  -----------------------------------------------------------------------
     */
    public boolean take (int philo, long timeout) throws InterruptedException {
        // we will try to acquire a set of left and right chop sticks (note:
        //   we must account for array indices (0 to N-1)
        int left =  (philo) % NumChopsticks;
        int right = (philo + 1) % NumChopsticks;

        // adds robustness if we block any other attempts for both chopsticks
        if(mutex.tryAcquire(timeout, TimeUnit.MILLISECONDS)) {
            // try to acquire a lock on both or don't pick up chopsticks
            if(self[left].tryAcquire(timeout, TimeUnit.MILLISECONDS)) {
                if (self[right].tryAcquire(timeout, TimeUnit.MILLISECONDS)) {
                    mutex.release();    // release the critical section
                    return true;
                } else {    // failed to get both chopsticks so release the left
                    self[left].release();
                }
            }
            mutex.release();    // release the critical section
        }
        return false;
    }

    /**
     * A full philosopher may drop his chopsticks pair at any time
     * @param philo The number of the hungry philosopher.
     */
    public void drop (int philo) {
        int left =  (philo) % NumChopsticks;
        int right = (philo + 1) % NumChopsticks;

        self[left].release();
        self[right].release();
    }
}
