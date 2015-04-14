package teacher.operation;

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
		isPaused=false;
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
	public static String getSecondFormat(long time){
		return (new SimpleDateFormat("ss.SSS")).format(new Date(time));
	}
	public static long getMilliFormat(String time){
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss.SSS");
			
			return (simpleDateFormat.parse(time).getTime()-simpleDateFormat.parse("00:00:00.000").getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 1;
	}
	public String toString(){
		return getMinuteFormat(get());
	}
	public static long getMilliFromSecs(String time){
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ss.SSS");
			
			return (simpleDateFormat.parse(time).getTime()-simpleDateFormat.parse("00.000").getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 1;
	}
	
}
