/*************************************************************************
	File: SimpleThread.java
	Author: Kelvin Sung

1. simple_thread: very simple running of threads
*************************************************************************/


import java.io.*;
import java.util.*;


public class SimpleThread {

///////////////////////////////////////////////////////////
// 
//  Driver program for SimpleThread.
// 
///////////////////////////////////////////////////////////

	public static void main(String args[]) {

		try { 
			//
			// Set up to read from keyboard
			InputStreamReader is = new InputStreamReader( System.in );
			BufferedReader br = new BufferedReader(is);
			String inputLine;
			StringTokenizer st;
			char cmd;
			int tid=0, numThreads = 1;

			while (numThreads > 0) {
				System.out.print("Number of threads to create [<0 to quit]:");
				inputLine = br.readLine();
				st = new StringTokenizer(inputLine);
				numThreads = Integer.parseInt(st.nextToken());

				//
				// now initialize and start all the threads
				//
				for (int i=0; i<numThreads; i++) {
					ComputeThread myThreads = new ComputeThread(tid);
					tid++;
					myThreads.start();
				}
			}

			System.out.println(" main thread going to quit ... ");

		} catch (IOException e)  {
			System.err.println("IO Error:" + e);
		}

	}

};
