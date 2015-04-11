package operation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Timer {
	private long startTime;
	private long timeOffset;
	private long currentTime;
	private boolean isPaused;
	public Timer(){
		startTime=0;
		isPaused=true;
		timeOffset=0;
	}
	public Timer(long startTime){
		this();
		this.startTime=startTime;
	}
	public void start(){
		startTime=getCurrentTimeInMillis()+startTime;
	}
	public void pause(){
		updateCurrentTime();
		isPaused=true;
	}
	public void resume(){
		isPaused=false;
		timeOffset+=getTimerTime()-currentTime;
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
	public long getTimerTime(){
		return getCurrentTimeInMillis()-startTime-timeOffset;
	}
	public long get(){
		updateCurrentTime();
		return currentTime;
	}
	public static String getMinuteFormat(long time){
		return (new SimpleDateFormat("mm:ss")).format(new Date(time));
	}
	public static long getMilliFormat(String time){
		try {
			return (new SimpleDateFormat("HH:mm:ss.SSS").parse(time).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 1;
	}
	public String toString(){
		return getMinuteFormat(get());
	}
}
