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
	
}
