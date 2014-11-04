/*************************************************************************
    File: ObjectSyncThread.java
    Author: Kelvin Sung
    Date: 4/26/2005

    ObjectSyncThread: simple thread showing waiting on any object
*************************************************************************/


import java.io.*;

//
// class: ObjectSyncThread
//
//         Really Really, *no really* stupid thread
//      continuously call object.wait() function!
//
class ObjectSyncThread extends Thread {

    //
    // instance variables:
    //        each thread will have one of this!!
    //
    //
    private    int                 threadID;
    private boolean                shouldDie;
    private Object                 syncObject;

///////////////////////////////////////////////////////////////////////////
//
// Constructor
// 
///////////////////////////////////////////////////////////////////////////
    ObjectSyncThread(int id, Object o) {
        threadID = id;
        syncObject = o;
        shouldDie = false;
        System.err.println("Creating new thread: id= [" + threadID + "]");
    }

    void GoDie() {
        shouldDie = true;
    }


    public void run() {
        while (!shouldDie) {
            synchronized(syncObject) {
                try {
                    syncObject.wait();
                } catch (Exception e) {
                    System.err.println("ObjectSyncThread:wait failed!" + e);
                }
            }
            System.err.println("\n");
            System.err.println("**** Object Sync Thread: id=[" + threadID + "]: just got woke up, continue ...");
            System.err.println("\n");
        }
        System.err.println("**** Object Sync Thread: id=[" + threadID + "]: going to die");
        return;
    }
};
