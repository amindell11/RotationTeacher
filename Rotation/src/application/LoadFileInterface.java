package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

/**
 *
 */
public class LoadFileInterface extends Application {
	
	@Override
	public void start(Stage stage) {
		TextField path = new TextField();
		Button parse=new Button("Parse");
			parse.setDefaultButton(true);
		Button file=new Button("From File...");
		Button load=new Button("Load");
		Button cancel=new Button("Cancel");
			cancel.setCancelButton(true);
		ScrollPane scroll=new ScrollPane();
		
		HBox fileLoad=new HBox();
			fileLoad.setSpacing(2);
			fileLoad.getChildren().addAll(path,parse);
		
		GridPane pane= new GridPane();
		GridPane.setHgrow(scroll,Priority.ALWAYS);
		GridPane.setVgrow(scroll,Priority.ALWAYS);
		pane.setPadding(new Insets(15,15,15,15));
		pane.setVgap(10);
		pane.setHgap(10);
		HBox.setHgrow(path,Priority.ALWAYS);
		pane.add(cancel,0,2);
		pane.add(load, 2, 2);
		pane.add(scroll,1,1);
		pane.add(fileLoad, 1, 0);
		Scene scene=new Scene(pane);
		stage.setScene(scene);
		stage.show();
	}
	public static void main(String[] args){
		launch();
	}
	
}
