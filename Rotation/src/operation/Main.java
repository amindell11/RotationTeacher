package operation;

import java.io.File;

import filemanagers.readers.Database;
import application.Overlay;

public class Main {
	static final String ablInfoPath = "XML";
	static final int updateTime = 500;
	private static Sequencer seq;
	public static void main(String[] args) {
		Overlay.launch(args);
		Database.indexFileNames(ablInfoPath);
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
	public static void reset(){
		seq.reset();
	}
	public static void load(File file){
		seq.init(file);
	}
	public static void update(){
		Overlay.display(seq.getQue());
		if(seq.shouldFlash())Overlay.flash();
		Overlay.displayTimers(seq.getQueTimes());
	}

}
