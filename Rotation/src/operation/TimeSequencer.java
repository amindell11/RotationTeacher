package operation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import filemanagers.readers.Database;

public class TimeSequencer extends Sequencer{
	private Timer timer;
	private List<Long> times;
	public TimeSequencer(){
		timer=new Timer();
		times=new ArrayList<Long>();
	}
	@Override
	public boolean isMoveOn() {
		return times.get(index)<=timer.get();
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
		timer=new Timer();
	}
	@Override
	public void readList() {
		super.readList();
		try {
			times=convertToLong(reader.readToList("time"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private List<Long> convertToLong(List<String> times){
		List<Long> temp = new ArrayList<Long>();
		for(String string:times){
			temp.add(Long.parseLong(string));
		}
		return temp;
	}

}
