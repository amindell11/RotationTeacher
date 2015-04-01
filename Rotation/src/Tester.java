import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.stage.Stage;

import javax.swing.JFrame;

import application.Overlay;
import filemanagers.readers.Ability;
import filemanagers.readers.Database;
import filemanagers.readers.LogParser;
import operation.Timer;

public class Tester extends Application{
	public static void main(String[] args) {
		Database.indexXML(new File("XML"));
		System.out.println(LogParser.lastLine(new File("logs/log.txt")));
		System.out.println(Database.getAbility("Hand Of Justice").getLogID());
	}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
