package operation;

import java.io.File;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import filemanagers.readers.Database;
import application.Overlay;

public class Main {
	static final String ablInfoPath = "XML";
	static final int updateTime = 250;
	private static Sequencer seq;
	public static void main() {
		seq=new TimeSequencer();
		Database.indexXML(new File(ablInfoPath));
		seq.init(new File("parse.tsv"));
		while (Overlay.isOpen()) {
			update();
			try {
				Thread.sleep(updateTime);
			} catch (InterruptedException e) {
				System.err.println("update timer was interrupted");
			}
		}
	}

	public static void start() {
		seq.start();
	}

	public static void pause() {
		seq.pause();
	}
	public static void resume() {
		seq.resume();
	}
	public static void reset(){
		seq.reset();
	}
	public static void load(File file){
		seq.init(file);
	}
	public static void update(){
		seq.update();
		Overlay.display(seq.getQue());
		//if(seq.shouldFlash())Overlay.flash();
		Overlay.displayTimers(((TimeSequencer) seq).getQueTimes());
	}

}
