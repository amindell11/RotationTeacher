package operation;

import java.io.File;
import java.io.IOException;
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
		try {
			setAbilities(Database.convertToAbilities(reader.readToList("Action")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Ability> getQue() {
		List<Ability> temp =new ArrayList<Ability>();
		for(int x=0;x<5;x++){
			temp.add(abilities.get(index+x));
		}
		System.out.println(temp);
		return temp;
	}

	public boolean shouldFlash() {
		return moveOn;
	}

	public abstract boolean isMoveOn();

	public void update() {
		if (isMoveOn() && !isPaused)
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
