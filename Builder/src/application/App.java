package application;

import java.io.File;
import java.util.HashMap;

import files.Ability;
import files.XMLManager;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 */
public class App extends Application {
	private static String baseClass;
	private static String advancedClass;
	private static String tree;
	
	public static void main(String[] args){
		System.out.println("hello");
		baseClass="Knight";
		advancedClass="Guardian";
		tree="Foca";
		launch();
	}
	@Override
	public void start(Stage stage) {
		System.out.println("hello");
		ChoiceBox classChooser=new ChoiceBox<String>();
		ChoiceBox specChooser=new ChoiceBox<String>();
		ScrollPane scroll=new ScrollPane();
		VBox abilityList= new VBox();
		HashMap<String,Ability> abilities=XMLManager.getAbilityFileNames(new File("XML/"+baseClass+".xml"),advancedClass,tree);
		for(String s:abilities.keySet()){
			HBox ability=new HBox();
			System.out.println(abilities.get(s));
			ImageView icon=new ImageView(abilities.get(s).getIcon());
			Label abilityName=new Label(s);
			ability.setAlignment(Pos.CENTER_LEFT);
			ability.setSpacing(5);
			Tooltip tip=new Tooltip(abilities.get(s).getDescription());
			tip.setMaxWidth(200);
			tip.setWrapText(true);
			Tooltip.install(ability, tip);
			ability.getChildren().addAll(icon,abilityName);
			abilityList.getChildren().add(ability);
		}
		abilityList.setSpacing(10);
		scroll.setMaxHeight(300);
		scroll.setContent(abilityList);
		Scene scene=new Scene(scroll);
		scene.getStylesheets().add("application/application.css");
		stage.setScene(scene);
		stage.show();
	}
	
}
