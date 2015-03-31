package operation;

import java.io.File;
import java.util.ArrayList;

import filemanagers.readers.Ability;
import filemanagers.readers.Database;
import filemanagers.readers.Reader;

public abstract class Sequencer {
	private Reader reader;
	private boolean moveOn;
	protected int index;
	private File list;
	private boolean isPaused;
	private ArrayList<Ability> abilities;

	public void start() {

	}

	public void resume() {
		isPaused = false;
	}

	public void pause() {
		isPaused = true;
	}

	public void reset() {
		moveOn = false;
		index = 0;
		isPaused = true;
	}

	public void init(File file) {
		list = file;
		reader = new Reader(file);
		readList();
		reset();
	}

	public void readList() {
		abilities = reader.readToList(Database.ACTIONS);
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
		moveOn = isMoveOn();
		if (moveOn && !isPaused)
			step();
	}

	private void step() {
		index++;
	}

}
