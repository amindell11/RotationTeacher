package operation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import filemanagers.readers.Database;

public class TimeSequencer extends Sequencer {
	private Timer timer;
	private List<Long> times;
	private int index0;

	public TimeSequencer() {
		timer = new Timer();
		times = new ArrayList<Long>();
		index0=0;
	}

	@Override
	public boolean isMoveOn() {
		return times.get(index) <= timer.get();
	}

	@Override
	public void start() {
		super.start();
		timer.start();
	}

	@Override
	public void resume() {
		super.resume();
		timer.resume();
	}

	@Override
	public void pause() {
		super.pause();
		timer.pause();
	}

	@Override
	public void reset() {
		super.reset();
		index=index0;
		timer = new Timer();
	}

	@Override
	public void readList() {
		super.readList();
		try {
			List<String> rawTimes = reader.readToList("Time");
			long t0 = 0;
			for (int x = 0; x < rawTimes.size(); x++) {
				String s = rawTimes.get(x);
				if (s.contains(" (pre-cast)")) {
					rawTimes.set(x, s.replace(" (pre-cast)", ""));
				} else {
					t0 = Timer.getMilliFormat(s);
					index0=x;
					break;
				}
			}
			times = convertToLong(rawTimes,t0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private List<Long> convertToLong(List<String> times) {
		List<Long> temp = new ArrayList<Long>();

		for (String string : times) {
			temp.add(Long.parseLong(string));
		}
		return temp;
	}
	public List<String> getQueTimes() {
		List<String> temp =new ArrayList<String>();
		for(int x=0;x<5;x++){
			long t=times.get(index+x)-timer.get();
			temp.add(Timer.getSecondFormat(t));
		}			
		return temp;
	}

	private List<Long> convertToLong(List<String> times,long t0) {
		List<Long> temp = new ArrayList<Long>();

		for (String string : times) {
			temp.add(Timer.getMilliFormat(string)-t0);
		}
		return temp;
	}
	@Override
	public void update(){
		super.update();
	}

}
