/*************************************************************************
	File: ComputeThread.java
	Author: Kelvin Sung
	Date: 4/12/2005

	3. write_to_file_server: what good is thread? here is a very very pretend simple
		thread server, that knows how to start write-to-file servers
*************************************************************************/

import java.io.*;
//
// class: ComputeThread
//
// 		pretend to be something important
//      when call my service function, I will pretend to write a *TON* of 
//		information	to the passed in file name, by writing a number and 
//		sleeping for 1 sec, do this 5 times.
//
class ComputeThread extends Thread {

	//
	// instance variables:
	//		Each thread will have one of this!!
	//
	//
	private PrintStream outFile; // file for output
	private String		fileName;
	private int threadID;

///////////////////////////////////////////////////////////////////////////
//
// Constructor
// 
///////////////////////////////////////////////////////////////////////////
	ComputeThread(int id, String nameString) {
		threadID = id;

		fileName = new String(nameString + id + ".out");
		FileOutputStream fo;
		try {
			fo = new FileOutputStream(fileName);
			outFile = new PrintStream(fo);
		} catch (Exception e) {
			System.err.println("Creating new file [" +  fileName + 
					"]: Caught Exception:" + e);
		}
	}

	public void run() {
		//
		// Do nothing, have a nice nap ...
		//
		outFile.println("Thread[" + threadID + "]: Pretend to work: ");
		for (int i = 0; i<10; i++) {
			try {
				sleep(1000);	// ms
			} catch (Exception e) {
				outFile.println("Thread Sleep Caught Exception:" + e);
			}
			outFile.println("Thread[" + threadID + 
				"]: writing lots of information iteration i=" + i);
		}
		System.err.println("Thread[" + threadID + "]: to file[" + fileName +
						"] pretend job really done ... quiting ... ");
		return;
	}

};
