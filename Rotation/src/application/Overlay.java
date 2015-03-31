package application;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 */
public class Overlay extends Application {
	
	@Override
	public void start(Stage stage) {
		Group root= new Group();
		Scene scene=new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public static boolean isOpen() {
		// TODO Auto-generated method stub
		return false;
	}

	public static void flash() {
		// TODO Auto-generated method stub
		
	}

	public static void display(Object que) {
		// TODO Auto-generated method stub
		
	}

	public static void displayTimers(Object queTimes) {
		// TODO Auto-generated method stub
		
	}
	
}
