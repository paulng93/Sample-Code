/*************************************************************************
    File: SimpleThread.java
    Author: Kelvin Sung
    Date: 4/25/2005

1. simple_thread: who is waiting on whom? And how to wake them all up?
*************************************************************************/


import java.io.*;
import java.util.*;


public class SimpleThread {

    public static void main(String args[]) {

        SyncWithMonitor monitorSync = new SyncWithMonitor();
        Object syncObj = new Object();    // we can syn on any object!

        MonitorSyncThread monitorSyncThreads[];
        monitorSyncThreads = new MonitorSyncThread[2];
        monitorSyncThreads[0] = new MonitorSyncThread(1, monitorSync);
        monitorSyncThreads[1] = new MonitorSyncThread(2, monitorSync);
        monitorSyncThreads[0].start();
        monitorSyncThreads[1].start();

        ObjectSyncThread objSyncThreads[];
        objSyncThreads = new ObjectSyncThread[2];
        objSyncThreads[0] = new ObjectSyncThread(3, syncObj);
        objSyncThreads[1] = new ObjectSyncThread(4, syncObj);
        objSyncThreads[0].start();
        objSyncThreads[1].start();


        try { 
            //
            // Set up to read from keyboard
            InputStreamReader is = new InputStreamReader( System.in );
            BufferedReader br = new BufferedReader(is);
            String inputLine;
            StringTokenizer st;
            int choice = 1;

            while (choice != 0) {
                System.out.println("0 - to quit");
                System.out.println("1 - Wake up one of the threads waiting on object ");
                System.out.println("2 - Wake up all the threads waiting on object ");

                System.out.println("");
                System.out.println("3 - Wake up one of the threads waiting on monitor ");
                System.out.println("4 - Wake up all the threads waiting on monitor ");

                System.out.println("");
                System.out.println("5 - Wake up all the threads waiting on object from MonitorSyncObject!");

                System.out.println("");
                System.out.print("Your Choice? ");

                inputLine = br.readLine();
                st = new StringTokenizer(inputLine);
                choice = Integer.parseInt(st.nextToken());

                switch (choice) {
                    case 1:
                        synchronized(syncObj) {
                            syncObj.notify();
                        }
                        break;

                    case 2:
                        synchronized(syncObj) {
                            syncObj.notifyAll();
                        }
                        break;

                    case 3:
                        monitorSync.WakeOneSyncWithMonitor();
                        break;
                    case 4:
                        monitorSync.WakeAllSyncWithMonitor();
                        break;

                    case 5:
                        monitorSync.WakeAllSyncWithObject(syncObj);
                        break;
                }
                Thread.sleep(1);
                System.out.println("");
            }

            System.out.println(" main thread going to quit ... ");

            monitorSyncThreads[0].GoDie();
            monitorSyncThreads[1].GoDie();
            monitorSync.WakeAllSyncWithMonitor();

            objSyncThreads[0].GoDie();
            objSyncThreads[1].GoDie();
            synchronized(syncObj) {
                syncObj.notifyAll();
            }

        } catch (IOException e)  {
            System.err.println("IO Error:" + e);
        } catch (Exception e) {
            System.err.println("Other exception" + e);
        }

    }

};
