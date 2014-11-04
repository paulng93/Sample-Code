/*************************************************************************
	File: ComputeThread.java
	Author: Kelvin Sung
	Date: 4/12/2005

	4. suspend_resume, see we have direct control over our worker threads
		but they say we shouldn ever suspend/resume our threads this way! WHY?
*************************************************************************/


import java.io.*;

//
// class: ComputeThread
//
// 		simple thread that performs simple operation.
//      starts and sleeps, and sleeps somemore
//   	until user says die.
//
class ComputeThread extends Thread {

	//
	// class variables:
	//		All threads share this!!
	//
	static private String sOurName;

	//
	// class methods
	static void SetClassVariables(String name) {
			sOurName = name;
	}


	//
	// instance variables:
	//		Each thread will have one of this!!
	//
	//
	private boolean shouldDie;
	private	int		threadID;
	private PrintStream outFile;
					// file for output

///////////////////////////////////////////////////////////////////////////
//
// Constructor
// 
///////////////////////////////////////////////////////////////////////////
	ComputeThread(int id) {
		shouldDie = false;

		threadID = id;

		String fileName = new String(sOurName + id + ".out");
		FileOutputStream fo;
		try {
			fo = new FileOutputStream(fileName);
			outFile = new PrintStream(fo);
		} catch (Exception e) {
			System.err.println("Creating new file [" +  fileName + 
				"]: Caught Exception:" + e);
		}
	}

	public void Terminate() {
		shouldDie = true;
		outFile.println("Thread[" + threadID + "]: Received to die signal");
	}


	private void Computation(int i) {
		//
		// Do nothing, have a nice nap ...
		//
		outFile.println("Thread[" + threadID + "]: Pretend to work: " + i);
		try {
			sleep(100);	// 100 ms
		} catch (Exception e) {
			outFile.println("Thread Sleep Caught Exception:" + e);
		}
		return;
	}


	public void run() {
		int i = 0;
		while (!shouldDie)
			 Computation(i++);
		outFile.println("Thread[" + threadID + "]: dieing ... ");
	}

};
