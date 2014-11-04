/*************************************************************************
	File: ComputeThread.java
	Author: Kelvin Sung

	1. simple_thread: very simple running of threads
*************************************************************************/


import java.io.*;

//
// class: ComputeThread
//
// 		Really Really, *no really* stupid threads
//    starts, prints out its id, sleeps, do this 10 times, and dies
//
class ComputeThread extends Thread {

	//
	// instance variables:
	//		each thread will have one of this!!
	//
	//
	private	int		threadID;

///////////////////////////////////////////////////////////////////////////
//
// Constructor
// 
///////////////////////////////////////////////////////////////////////////
	ComputeThread(int id) {
		threadID = id;
		System.err.println("Creating new thread: id= [" + threadID + "].  From Java:" + this);

	}



	public void run() {
		// 
		// This is where the thread does its work ...
		// Do nothing, have a nice nap ...
		//
		int i = 0;
		for (i=0; i<5; i++) {
			System.err.println("Thread[" + threadID + "]: Pretend to work: i=" + i);
			try {
				sleep(200);	// 100 ms
			} catch (Exception e) {
				System.err.println("Thread Sleep Caught Exception:" + e);
			}
		}
		System.err.println("***> Thread[" + threadID 
			+ "]: done pretend to work ... quiting ...");
		return;
	}
};
