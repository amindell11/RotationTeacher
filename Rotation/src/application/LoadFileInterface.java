package application;

import java.io.File;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import operation.Main;
import util.filemanagers.writers.HTMLParser;
import util.filemanagers.writers.Writer;

/**
 *
 */
public class LoadFileInterface extends Application {
	private File file;
	private ScrollPane scroll;
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
			
		scroll=new ScrollPane();
		
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
		String[][] s=HTMLParser.parseTable(URL);
		writeScrollBox(s);
		Writer.TSVWrite(file, s);
	}
	public void writeScrollBox(String[][] list){
		VBox yBox=new VBox();
		yBox.setFillWidth(true);
		scroll.setFitToWidth(true);
		for(int x=1;x<list.length;x++){
			HBox xBox=new HBox();
			xBox.maxWidth(Double.MAX_VALUE);
			if(x%2==0)xBox.setStyle("-fx-background-color: #E0E0E0;");
			xBox.setSpacing(20);
			for(int y=0;y<list[0].length;y++){
				xBox.getChildren().add(new Label(list[x][y]));
			}
			yBox.getChildren().add(xBox);
		}
		scroll.setContent(yBox);
	}
	public static void main(String[] args){
		launch();
	}
	public void writeScrollDown(String[][] list){
		HBox box=new HBox();
		box.setSpacing(3);
		for(int x=0;x<list[0].length;x++){
			VBox col=new VBox();
			col.setFillWidth(true);
			for(int y=0;y<list.length;y++){
				Label l=new Label(list[y][x]);
				if(y%2==0)l.setStyle("-fx-background-color: #E0E0E0;");
				col.getChildren().add(l);
			}
			box.getChildren().add(col);
		}
		scroll.setContent(box);
	}
	
}
