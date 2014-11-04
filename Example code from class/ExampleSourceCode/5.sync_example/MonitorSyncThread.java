/*************************************************************************
    File: MonitorSyncThread.java
    Author: Kelvin Sung
    Date: 4/26/2005

    MonitorSyncThread: simple thread to show waiting on a monitor function
*************************************************************************/


import java.io.*;

//
// class: MonitorSyncThread
//
//         Really Really, *no really* stupid thread
//      continuously call SyncWithMonitor::WaitOnSyncWithMonitorSync() funtion
//
class MonitorSyncThread extends Thread {

    //
    // instance variables:
    //        each thread will have one of this!!
    //
    //
    private    int              threadID;
    private boolean             shouldDie;
    private SyncWithMonitor     monitorSyncObj;

///////////////////////////////////////////////////////////////////////////
//
// Constructor
// 
///////////////////////////////////////////////////////////////////////////
    MonitorSyncThread(int id, SyncWithMonitor t) {
        threadID = id;
        monitorSyncObj = t;
        shouldDie = false;
        System.err.println("Creating new thread: id= [" + threadID + "]");
    }

    void GoDie() {
        shouldDie = true;
    }


    public void run() {
        while (!shouldDie) {
            monitorSyncObj.WaitOnSyncWithMonitor(threadID);
            System.err.println("\n");
            System.err.println("**** Monitor Sync Thread: id=[" + threadID + "]: just got woke up, continue ...");
            System.err.println("\n");
        }
        System.err.println("**** Monitor Sync Thread: id=[" + threadID + "]: going to die");
        return;
    }
};
