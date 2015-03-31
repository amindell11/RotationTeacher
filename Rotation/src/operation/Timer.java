package operation;

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
		Date date = new Date();
		return date.getTime();
	}
	public long get(){
		long time = getCurrentTimeInMillis()-startTime;
		if(time>0)
		return time;
		return 0;
	}
}
