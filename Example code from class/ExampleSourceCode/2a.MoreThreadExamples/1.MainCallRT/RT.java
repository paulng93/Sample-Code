
import java.io.*;

class RT extends Thread {

  static int RTID = -1;

	static public void SetState(int callerId, int rtID) {
			System.err.println("Caller id=" + callerId + " Set RTId=" + rtID);
			RTID = rtID;
	}

  public void RTBumpCount(int callerId) {
			count += 10;
			System.err.println("RTBumpCount: callerID=" + callerId + " (rtid=" + id + ") count:" + count);
	}

	public int	id;
	private int count;

	RT() {
	    count = 10;
	}

	public void run() {
		
		System.err.println("RT running id=" + id);
		
		while (RT.RTID < id);  // spins
		
		if (count > 10)
		    CT.MyFunc(id);
		
		RTBumpCount(id);    
	}
};
