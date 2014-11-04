/*************************************************************************
	File: ComputeThread.java
	Author: Kelvin Sung
	Date: 4/12/2005

	2. variable_shading_and_function_calls, just who owns what, and how
		how many copies of stuff are there?
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
	// Class variable:
	//		This is on the Heap, this var is shared among all threads!
	//
	static public int sAVar;
		
	//
	// instance variables:
	//		each thread will have one of this!!
	//
	private	int		threadID;
	public  int     myVar;

///////////////////////////////////////////////////////////////////////////
//
// Constructor
// 
///////////////////////////////////////////////////////////////////////////
	ComputeThread(int id) {
		threadID = id;
		myVar = 1;
		System.err.println("Creating new thread: id= [" + threadID 
				+ "] and myVar=" + myVar);
	}

	public void MyFunction() {
		System.err.println("From threadid=[" + threadID + "]");
		System.err.println("	myVar = " + myVar);
		System.err.println("	ComputeThread.sAVar = " + ComputeThread.sAVar);
		System.err.println("	SimpleThread.sAVar  = " + SimpleThread.sAVar);
		System.err.println("	** changing SimpleThread.sAVar  to myid of " 
				+ (threadID+10) * 2);
		System.err.println("	** changing ComputeThread.sAVar to myid of " 
				+ (threadID+10) * 2);
		SimpleThread.sAVar = (threadID+10)*2;
		ComputeThread.sAVar = (threadID+10)*2;
		System.err.println("	ComputeThread.sAVar = " + ComputeThread.sAVar);
		System.err.println("	SimpleThread.sAVar  = " + SimpleThread.sAVar);
		System.err.println("  ");
	}

	public void run() {
		// 
		// This is where the thread does its work ...
		// Do nothing, have a nice nap ...
		//
		do {
			try {
				sleep(200);	// 100 ms
			} catch (Exception e) {
				System.err.println("Thread Sleep Caught Exception:" + e);
			}
		} while (myVar != 0);
		return;
	}
};
