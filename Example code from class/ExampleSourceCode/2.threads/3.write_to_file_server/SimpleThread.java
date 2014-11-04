/*************************************************************************
	File: SimpleThread.java
	Author: Kelvin Sung
	Date: 4/12/2005

	3. write_to_file_server: what good is thread? here is a very very pretend simple
	thread server, that knows how to start write-to-file servers
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
			char cmd = 'a';
			int tid = 0;


			System.out.println("At your service!");
			System.out.println("At your service!");
			System.out.println("If you enter a string, I will prtend that I am");
			System.out.println("outputing lots of information to that file, like");
			System.out.println("I just opened a database and you tell me which");
			System.out.println("file to output to, thus, I am providing a service: ");
			System.out.println("Like ... file output service");
			System.out.println("Notice my code is very simple, I am very efficient");
			System.out.println("and I can response to your request very rapidly");
			System.out.println("... without forking new process, but with spawning");
			System.out.println("new threads ... cool eh?");
			System.out.println(" ");
			while (cmd != 'q') {
				System.out.print("What is the output file name [q to quit]: ");
				inputLine = br.readLine();
				cmd = inputLine.charAt(0);

				if (cmd != 'q') {
					System.out.println(" ... ");
					System.out.println(" hold on ... createing my writer thread");
					ComputeThread writer = new ComputeThread(tid, inputLine);

					System.out.println(" ...  now starting it to do the work ...");
					writer.start();
					System.out.println("now my writer thread is writing for you");
					System.out.println("it will take about 2 seconds"); 
					System.out.println("in the mean time, I am free to help you");
					System.out.println("pretty fast response? Cool eh?");
					System.out.println(" ");
					tid++;
				}

			}

			System.out.println(" ... ok ... bye ... fun?");
			
		} catch (IOException e)  {
			System.err.println("IO Error:" + e);
		}

	}

};
