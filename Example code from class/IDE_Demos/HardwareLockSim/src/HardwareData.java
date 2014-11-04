public class HardwareData {

    private boolean value = false;

    public HardwareData( boolean initialValue ) {
        this.value = initialValue;
    }

    public boolean get() {
        return value;
    }

    public synchronized void set( boolean newValue ) {
        this.value = newValue;
    }

    /**
     * TestAndSet - atomic simulation of a hardware test and set instruction
     * @param newValue new state to be set if current value is false
     * @return returns the current state that is being tested on this call
     */
    public synchronized boolean TestAndSet( boolean newValue ) {
        boolean oldValue = this.get();
        if(!oldValue) this.set( newValue );
        return oldValue;
    }

    /**
     * swap - atomic simulation of a hardware swap instruction
     * @param other - other lock
     */
    public synchronized void swap(HardwareData other) {
        boolean temp = this.get();
        this.set( other.get() );
        other.set( temp );
    }
}