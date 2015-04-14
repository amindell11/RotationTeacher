package teacher.operation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import util.Ability;
import util.Database;
import util.filemanagers.readers.Reader;

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
		moveOn = false;
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
			int i = index+x;
			Ability a;
			if(i<abilities.size())
				a=abilities.get(i);
			else a=null;
			temp.add(a);
		}
		return temp;
	}

	public boolean shouldFlash() {
		return moveOn;
	}

	public abstract boolean isMoveOn();

	public void update() {
		moveOn = isMoveOn();
		if ( moveOn && !isPaused)
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
