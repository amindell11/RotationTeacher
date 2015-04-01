package operation;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import filemanagers.readers.Ability;
import filemanagers.readers.Database;
import filemanagers.readers.Reader;

public abstract class Sequencer {
	protected Reader reader;
	private boolean moveOn;
	protected int index;
	protected File file;
	protected boolean isPaused;
	protected List<Ability> abilities;

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
		this.file = file;
		reader = new Reader(file);
		readList();
		reset();
	}

	public void readList() {
		setAbilities(Database.convertToAbilities(reader.readToList(Database.ACTIONS)));
	}

	public List<Ability> getQue() {
		return null;
	}

	public boolean shouldFlash() {
		return moveOn;
	}

	public List<String> getQueTimes() {
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

	public List<Ability> getAbilities() {
		return abilities;
	}

	public void setAbilities(List<Ability> abilities) {
		this.abilities = abilities;
	}

}
