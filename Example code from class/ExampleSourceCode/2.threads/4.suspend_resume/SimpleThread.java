/*************************************************************************
	File: SimpleThread.java
	Author: Kelvin Sung
	Date: 4/12/2005

	4. suspend_resume, see we have direct control over our worker threads
	but they say we shouldn ever suspend/resume our threads this way! WHY?
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


			System.out.print("Number of threads to create:");
			int tid, numThreads = 0;
			inputLine = br.readLine();
			st = new StringTokenizer(inputLine);
			numThreads = Integer.parseInt(st.nextToken());

			ComputeThread.SetClassVariables("ReallyStupidThreads");

			ComputeThread myThreads[] = new ComputeThread[numThreads];

			//
			// now initialize and start all the threads
			//
			for (int i=0; i<numThreads; i++) {
				myThreads[i] = new ComputeThread(i);
				myThreads[i].start();
			}

			//
			System.out.println("Type q to quit");
			inputLine = " ";
			while (inputLine.charAt(0) != 'q') {
				System.out.print("Command [s r q]: ?");
				inputLine = br.readLine();
				cmd = inputLine.charAt(0);
				if ( cmd=='r' || cmd=='s' ) {
					System.out.print("Which Thread [0-" + (numThreads-1) + "]: ");
					inputLine = br.readLine();
					st = new StringTokenizer(inputLine);
					tid = Integer.parseInt(st.nextToken());
					if (tid >= 0 && tid < numThreads) {
						if (cmd == 'r') 
							myThreads[tid].resume();
						else
							myThreads[tid].suspend();
					}
				}
			}

			System.out.println(" ... ok ... informing all threads to quit ... ");
			
			//
			// Termination Signal from the user ...
			//
			// now signal all threads to die ...
			//
			for (int i=0; i<numThreads; i++) {
				myThreads[i].resume();
				myThreads[i].Terminate();
			}

			System.out.println("Main User Interaction Thread exiting ... !");

		} catch (IOException e)  {
			System.err.println("IO Error:" + e);
		}

	}

};
