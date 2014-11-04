/*************************************************************************
    File: SyncWithMonitor.java
    Author: Kelvin Sung
    Date: 4/26/2005

    SyncWithMonitor: simple object to show synchronized methods and the
                    wait/notify/notifyAll functions
*************************************************************************/

public class SyncWithMonitor {

    SyncWithMonitor() {
    }

    public synchronized void WaitOnSyncWithMonitor(int tid) {
        try {
            wait();
        } catch (Exception e) {
            System.err.println("SyncWithMonitor:WaitOnSyncWithMonitor wait faile!" + e);
        }
        System.err.println("SyncWithMonitor:WaitOnSyncWithMonitor is awake tid=" + tid);
    }

    public synchronized void WakeAllSyncWithMonitor() {
        notifyAll();
    }

    public synchronized void WakeOneSyncWithMonitor() {
        notify();
    }


    public void WakeAllSyncWithObject(Object syncObject) {
        synchronized(syncObject) {
            syncObject.notifyAll();
        }
    }

}
