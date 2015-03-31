package operation;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Timer {
	private long startTime;
	private long timeOffset;
	private long currentTime;
	private boolean isPaused;
	public Timer(){
		startTime=0;
		isPaused=false;
		timeOffset=0;
	}
	public Timer(long startTime){
		this.startTime=startTime;
	}
	public void start(){
		startTime=getCurrentTimeInMillis();
	}
	public void pause(){
		updateCurrentTime();
		isPaused=true;
	}
	public void resume(){
		isPaused=false;
		timeOffset+=getTimerTime-currentTime;
		updateCurrentTime();
	}
	private long getCurrentTimeInMillis() {
		Date date = new Date();
		return date.getTime();
	}
	public void updateCurrentTime(){
		if(!isPaused){
			currentTime=getTimerTime();
		}
	}
	public void getTimerTime(){
		return getCurrentTimeInMillis()-startTime-timeOffset;
	}
	public long get(){
		updateCurrentTime();
		return currentTime;
	}
	public String getMinuteFormat(){
		long time=get();
		return (new SimpleDateFormat("mm:ss")).format(new Date(time));
	}
}
