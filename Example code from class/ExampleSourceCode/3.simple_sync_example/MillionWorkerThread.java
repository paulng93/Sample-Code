//************************************************************************
//    File: MillionWorkerThread.java
//    Author: Kelvin Sung
//    Date: 4/12/2005
//    
//    accepts MyWork, and in a very tight loop, do MyWork 1 million times
//*************************************************************************


import java.io.*;

//
// class: MillionWorkerThread
//
//
class MillionWorkerThread extends Thread {
    //
    // to have a unique number for each of them
    private    int        threadID;

    private MyWork    workLoad;    // this is the assignment passed in

    MillionWorkerThread(int id, MyWork w) {
        threadID = id;
        workLoad = w;
        System.err.println("Creating new thread: id= [" + threadID + "]");
    }


    public void run() {
        for (int i=0; i<1000000; i++) {
            //synchronized(workLoad) {
                workLoad.DoMyWork();
            //}
        }

        System.err.println("Thread:id= [" + threadID + "] is done with the work");
        return;
    }
}
