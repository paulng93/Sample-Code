/*************************************************************************
	File: SimpleThread.java
	Author: Kelvin Sung
	Date: 4/12/2005

	
	2. variable_shading_and_function_calls, just who owns what, and how
	how many copies of stuff are there?
*************************************************************************/

import java.io.*;
import java.util.*;


public class SimpleThread {

	// Class variable:
	//	This is on the heap, all threads sees this same variable
	static public int sAVar;

///////////////////////////////////////////////////////////
// 
//  Driver program for SimpleThread.
// 
///////////////////////////////////////////////////////////

	public static void main(String args[]) {

		try { 

			SimpleThread.sAVar = -1;


			//
			// Set up to read from keyboard
			InputStreamReader is = new InputStreamReader( System.in );
			BufferedReader br = new BufferedReader(is);
			String inputLine;
			StringTokenizer st;
			char cmd;
			int tid = 1, num = 1;
			ComputeThread myThreads[] = new ComputeThread[2];
				// create and start the threads ...
			for (int i=0; i<2; i++) {
				myThreads[i] = new ComputeThread(i);
				myThreads[i].start();
			}

			while ( (tid == 0) || (tid == 1) ) {
				System.out.print("Which Thread to work on [0 or 1, any other");
				System.out.print(" number to quit]: ");
				inputLine = br.readLine();
				st = new StringTokenizer(inputLine);
				tid = Integer.parseInt(st.nextToken());

				if ( (tid == 0) || (tid == 1) ) { 
					myThreads[tid].myVar = ((num+2) * 10) + tid;

					System.err.println("From Main Thread:");
					System.err.println("	Thread[0].myVar=" + myThreads[0].myVar 
							+ "	Thread[1].myVar=" + myThreads[1].myVar);
					System.err.println("	ComputeThread.sAVar = " 
							+ ComputeThread.sAVar);
					System.err.println("	SimpleThread.sAVar  = " 
							+ SimpleThread.sAVar);
					System.err.println(" ********* now going to call thread:" 
							+ tid + " ... ******** ");

					myThreads[tid].MyFunction();

					System.err.println(" ********* main: just returned from thread:" 
							+ tid + " ... ***** ");
					System.err.println("From Main Thread:");
					System.err.println("	Thread[0].myVar=" + myThreads[0].myVar 
							+ "	Thread[1].myVar=" + myThreads[1].myVar);
					System.err.println("	ComputeThread.sAVar = " 
							+ ComputeThread.sAVar);
					System.err.println("	SimpleThread.sAVar  = " 
							+ SimpleThread.sAVar);
					System.err.println("	");
					System.err.println("	");
				}
				num++;
			}

			System.out.println(" main thread going to quit ... ");
			//
			// Make sure children threads all sees they should quit.
			myThreads[0].myVar = 0;
			myThreads[1].myVar = 0;

		} catch (IOException e)  {
			System.err.println("IO Error:" + e);
		}

	}

};
