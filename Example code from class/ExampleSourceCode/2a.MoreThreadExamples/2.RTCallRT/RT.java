
import java.io.*;

class RT extends Thread {

  static int RTID = -1;

	static public void SetState(int callerId, int rtID) {
			System.err.println("Caller id=" + callerId + " Set RTId=" + rtID);
			RTID = rtID;
	}

  public void RTFunc(int callerId) {
			System.err.println("RTFunc: callerID=" + callerId + " (rtid=" + id + ")");
	}

	public int	id;
	public RT refToOther;

	RT() {	
	}

	public void run() {
		System.err.println("RT running id=" + id);
		refToOther.RTFunc(id);		
		
		while (id > RTID);
		
		RT.SetState(id, id+1);
		RTFunc(id);
		
		CT.MyFunc(id);
	}
};
