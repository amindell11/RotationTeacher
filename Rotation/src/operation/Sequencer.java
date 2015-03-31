package operation;

import java.io.File;
import java.util.ArrayList;

import filemanagers.readers.Ability;
import filemanagers.readers.Reader;

public abstract class Sequencer {
	private Reader r;
	private boolean moveOn;
	private int index;
	private File list;
	private boolean isPaused;
	private ArrayList<Ability> abilities;
	public void start() {
		
	}
	public void resume(){
		isPaused=false;
	}
	public void pause() {
		isPaused=true;
	}

	public void reset() {
		init(list);
	}

	public void init(File file) {
		list=file;
	}

	public Object getQue() {
		return null;
	}

	public boolean shouldFlash() {
		return moveOn;
	}

	public Object getQueTimes() {
		return null;
	}
	public abstract boolean isMoveOn();

	public void update() {
		moveOn=isMoveOn();
		if(moveOn&&!isPaused)step();
	}
	private void step(){
		index++;
	}

}
