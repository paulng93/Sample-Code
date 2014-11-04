//************************************************************************
//    File: SimpleThread.java
//    Author: Kelvin Sung
//    Date: 4/20/2005
//    
//     1. spawns to MillionWorkerThread to do MyWork (each will increment 
//        the count by a million. 
//
//    2. Between: 
//        MyWork.java
//        MillionWorkerThread.java
//     there are three places with "synchronized" keyword (all commented out)
//     in this case, anyone of the three will properly implement what we need.
//*************************************************************************/


import java.io.*;
import java.util.*;

public class SimpleThread {

    public static void main(String args[]) {

        MyWork workLoad = new MyWork();

        try { 
            //
            // Set up to read from keyboard
            InputStreamReader is = new InputStreamReader( System.in );
            BufferedReader br = new BufferedReader(is);
            String inputLine;
            StringTokenizer st;
            char cmd;
            int numThreads = 1;

            System.out.print("Number of threads to create [<0 to quit]:");
            inputLine = br.readLine();
            st = new StringTokenizer(inputLine);
            numThreads = Integer.parseInt(st.nextToken());

            MillionWorkerThread myThreads[] = new MillionWorkerThread[numThreads];

            //
            // now initialize and start all the threads
            //
            for (int i=0; i<numThreads; i++) {
                myThreads[i] = new MillionWorkerThread(i, workLoad);
                myThreads[i].start();
            }

            System.out.println("main thread going to quit ... ");
            workLoad.ShowMyWork();
            System.out.println("What is going on?");
            for (int i=0; i<numThreads; i++) {
                try {
                    myThreads[i].join();
                } catch (Exception e) {
                    System.err.println("Join caught exception:" + e);
                }
            }
            workLoad.ShowMyWork();

        } catch (IOException e)  {
            System.err.println("IO Error:" + e);
        }

    }

};
