/**
 * This program gives an example of how deadlock can occur between threads.
 * This program differs from the book as each thread will sleep a random
 * amount of time between calls to the synchronized statement. It may require
 * several runs of the program to deadlock the threads.
 *
 * Figure 7.4
 *
 * @author Gagne, Galvin, Silberschatz
 * Operating System Concepts with Java - Eighth Edition
 * Copyright John Wiley & Sons - 2010.
 */


import java.util.concurrent.locks.*;

class NamedReentrantLock extends ReentrantLock {
  private String name;
  public NamedReentrantLock( String name ) {
    this.name = name;
  }
  public String toString() {
    return String.format( "Lock-%s", name );
  }
}

class Deadlockable implements Runnable {
  private String name;
  private Lock first, second;
  
  public Deadlockable( String name, Lock first, Lock second ) {
    this.name = name;
    this.first = first;
    this.second = second;
  }
  
  public void run() {
    try {
      System.out.printf( "Thread %s wants to acquire lock %s\n", name, first );
      first.lock();
      System.out.printf( "Thread %s has now acquired lock %s\n", name, first );
      SleepUtilities.nap( 3 );
      System.out.printf( "Thread %s wants to acquire lock %s\n", name, second );
      second.lock();
      System.out.printf( "Thread %s has now acquired lock %s\n", name, second );
    }
    finally {
      first.unlock();
      second.unlock();
    }
  }
}

public class DeadlockExample {  
  public static void main( String arg[] ) {         
    Lock lock1 = new NamedReentrantLock( "1" );
    Lock lock2 = new NamedReentrantLock( "2" );
    
    // JFM: Note that Thread "A" is passed lockX (first) and lockY (second)
    //            and Thread "B" is passed lockY (first) and lockY (second)
    Thread threadA = new Thread( new Deadlockable( "A", lock1, lock2 ) );
    Thread threadB = new Thread( new Deadlockable( "B", lock2, lock1 ) );
    
    threadA.start();
    threadB.start();
  }
}
