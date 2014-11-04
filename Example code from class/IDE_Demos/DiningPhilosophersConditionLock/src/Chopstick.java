import java.util.concurrent.locks.*;

/**
 * Created by Steve Dame on 2/22/14.
 * Creates a class Chopstick that allows Dining Philosophers that are hungry to attempt to acquire so that they may
 * eat.
 */
public class Chopstick {
    private int NumChopsticks;
    private enum State {PICKED_UP, PUT_DOWN}
    private State[] chopstick;
    private Condition[] self;

    /**
     * Constructor.
     *
     * @param numPhils The number of dining philosophers.
     */
    public Chopstick(int numPhils) {
        // initialize the number of chopsticks
        NumChopsticks = numPhils;
        // declare an array of Condition variables and chopsticks
        self = new Condition[numPhils];
        chopstick = new State[numPhils];
        for (int i = 0; i < numPhils; i++) {
            Lock key = new ReentrantLock();
            self[i] = key.newCondition();
            chopstick[i] = State.PUT_DOWN;
        }
    }

    /**
     * A hungry philosopher attempts to pick up his left and right chopsticks.
     *
     * @param philo The number of the hungry philosopher.
     * @throws InterruptedException -----------------------------------------------------------------------
     */
    public synchronized boolean take(int philo, long timeout) throws InterruptedException {
        // we will try to acquire a set of left and right chop sticks (note:
        //   we must account for array indices (0 to N-1)
        int left = (philo) % NumChopsticks;
        int right = (philo + 1) % NumChopsticks;

        // each tryLock will test and set if true
        if (chopstick[left] == State.PUT_DOWN) {
            if (chopstick[right] == State.PUT_DOWN) {
                chopstick[left] = State.PICKED_UP;
                chopstick[right] = State.PICKED_UP;
                return true;
            }
        }
        wait();
        return false;
    }

    /**
     * A full philosopher may drop his chopsticks pair at any time
     *
     * @param philo The number of the hungry philosopher.
     */
    public synchronized void drop(int philo) {
        int left = (philo) % NumChopsticks;
        int right = (philo + 1) % NumChopsticks;
        chopstick[left] = State.PUT_DOWN;
        chopstick[right] = State.PUT_DOWN;
        notify();
    }
}
