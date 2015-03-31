package operation;

import java.io.File;
import java.util.ArrayList;

public class TimeSequencer extends Sequencer{
	private Timer timer;
	private ArrayList<Long> times;
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
	}

	@Override
	public void reset() {
		super.reset();
	}	

}
