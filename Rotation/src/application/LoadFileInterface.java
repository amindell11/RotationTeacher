package application;

import java.io.File;
import java.io.IOException;

import filemanagers.writers.HTMLParser;
import filemanagers.writers.Writer;
import operation.Main;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 */
public class LoadFileInterface extends Application {
	private File file;
	@Override
	public void start(Stage stage) {
		TextField path = new TextField();
		FileChooser fileChooser=new FileChooser();
			fileChooser.setInitialDirectory(new File("logs"));
		Button parse=new Button("Parse");
			parse.setDefaultButton(true);
		Button fileButton=new Button("From File...");
		Button load=new Button("Load");
		Button cancel=new Button("Cancel");
			cancel.setCancelButton(true);
			
		parse.setOnAction(event -> {
			parse(path.getText());
		});
		fileButton.setOnAction(event -> {
			file=fileChooser.showOpenDialog(null);
		});
		load.setOnAction(event -> {
			Main.load(file);
			stage.close();
		});
		cancel.setOnAction(event -> {
			stage.close();
		});
			
		ScrollPane scroll=new ScrollPane();
		
		HBox fileLoad=new HBox();
		HBox.setHgrow(path,Priority.ALWAYS);
			fileLoad.setSpacing(2);
			fileLoad.getChildren().addAll(path,parse,fileButton);
		
		GridPane pane= new GridPane();
		GridPane.setHgrow(scroll,Priority.ALWAYS);
		GridPane.setVgrow(scroll,Priority.ALWAYS);
			pane.setPadding(new Insets(15,15,15,15));
			pane.setVgap(10);
			pane.setHgap(10);
			pane.add(cancel,0,2);
			pane.add(load, 2, 2);
			pane.add(scroll,1,1);
			pane.add(fileLoad, 1, 0);
		Scene scene=new Scene(pane);
		stage.setScene(scene);
		stage.show();
	}
	private void parse(String URL) {
		file=new File("parse.tsv");
		Writer.TSVWrite(file, HTMLParser.parseTable(URL));
	}
	public static void main(String[] args){
		launch();
	}
	
}
