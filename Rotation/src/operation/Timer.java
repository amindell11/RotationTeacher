package operation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Timer {
	private long startTime;
	public Timer(){
		startTime=0;
	}
	public long start(){
		startTime=getCurrentTimeInMillis();
		return startTime;
	}
	private long getCurrentTimeInMillis() {
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		return 0;
	}
}
