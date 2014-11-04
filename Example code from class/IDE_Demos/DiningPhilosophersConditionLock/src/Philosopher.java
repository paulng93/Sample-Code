/**
 * Created by Steve on 2/22/14.
 * Creates a class Philosopher that thinks, gets hungry, tries to pick up chopsticks and eat, then returns to thinking
 */
public class Philosopher implements Runnable {
    private enum State {EATING, HUNGRY, THINKING};
    private final static int MAX_THINKING_TIME = 3000;
    private final static int MAX_EATING_TIME = 2000;

    private State PhilosopherState;
    private int me;

    private Chopstick chopsticks;

    /**
     * Constructor.
     * @param myPhiloNumber The number of dining philosophers.
     * @param theChopsticks object reference to the chopsticks on the table
     */
    public Philosopher(int myPhiloNumber, Chopstick theChopsticks) {
        PhilosopherState = State.THINKING;
        me = myPhiloNumber;
        this.chopsticks = theChopsticks;
        System.out.println("Initializing P "+ myPhiloNumber + " to THINKING");
    }

    /**
     * The philosopher thread runner
     */
    public void run() {
        while (true) {
            try {
                switch(PhilosopherState) {
                    case THINKING:
                        SleepUtilities.nap(MAX_THINKING_TIME);
                        PhilosopherState = State.HUNGRY;
                        break;
                    case HUNGRY:
                        if(chopsticks.take(me, 1000L)){
                            PhilosopherState = State.EATING;
                        }
                        break;
                    case EATING:
                        SleepUtilities.nap(MAX_EATING_TIME);
                        chopsticks.drop(me);
                        PhilosopherState = State.THINKING;
                        break;
                    default:
                        break;
                }

            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            System.out.println("Philosopher " + me + " is " +
                    ((PhilosopherState == State.THINKING) ? "THINKING" :
                            ((PhilosopherState == State.HUNGRY) ? "HUNGRY" : "EATING")));
        }
    }
}
