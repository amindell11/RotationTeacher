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
import operation.Timer;

public class Tester extends Application{
	public static void main(String[] args) {
		Overlay.launch();
		List<Ability> s=new ArrayList<Ability>();
		s.add(new Ability(0,"armament1","armament1","",10000));
		Overlay.display(s);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
