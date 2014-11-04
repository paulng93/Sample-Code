
import java.io.*;
import java.util.*;


public class CT {

  public static int WaitID = 0;
	public static void MyFunc(int callerId) {
			System.err.println("CT callerId=" + callerId);
	}

	public static void main(String args[]) {
			int id;
			RT myThreads[] = new RT[2];

			myThreads[0] = new RT();
			myThreads[1] = new RT();
      
			id = 2;
			myThreads[0].id = 0;
			myThreads[1].id = 1;
			
			myThreads[0].refToOther = myThreads[1];
			myThreads[1].refToOther = myThreads[0];

			myThreads[0].start();
			myThreads[1].start();

			myThreads[1].RTBumpCount(id);
			
			while (CT.WaitID < 1);
			  RT.SetState(id, 0);
			
			while (CT.WaitID < 2);
			  RT.SetState(id, 1);
			  
			RT.SetState(id, id);
	}

};
