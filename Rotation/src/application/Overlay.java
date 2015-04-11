package application;

import java.io.File;
import java.util.List;
import java.util.TimerTask;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import operation.Main;
import operation.Timer;
import filemanagers.readers.Ability;

/**
 *
 */
public class Overlay extends Application {
	private static boolean open;
	public static ImageView[] que;
	private static Label[] queTimes;
	private static ImageView current;
	int x;
	int y;

	@Override
	public void start(Stage stage) {
		open=true;
		Button play = new Button("Start");
			play.setDefaultButton(true);
		Button load = new Button("Load");
		Button reset = new Button("Reset");
		Button close = new Button("Close");
			close.setCancelButton(true);
		ImageView icon = new ImageView("file:sys/gear.png");			
			icon.setPreserveRatio(true);
			icon.setFitWidth(15);
		Button settings = new Button("", icon);
			settings.setMaxSize(110, 110);

		que = new ImageView[5];
		for (int x = 0; x < que.length; x++) {
			que[x] = new ImageView();
			loadIcon(que[x], "");
		}
		queTimes = new Label[4];
		for (int x = 0; x < queTimes.length; x++) {
			queTimes[x] = new Label("00.000");
		}
		VBox[]timeAndIconList=new VBox[4];
		for(int x=0;x<timeAndIconList.length;x++){
			timeAndIconList[x]=new VBox();
			timeAndIconList[x].getChildren().addAll(queTimes[x],que[x+1]);
		}
		
		current = que[0];
			current.setFitHeight(90);
			current.setFitWidth(90);
			loadIcon(current, "");
		
		HBox controls = new HBox();
			controls.getChildren().addAll(play, load, reset, close, settings);

		HBox box = new HBox();
			box.setSpacing(2);
			box.getChildren().addAll(timeAndIconList);
		
		VBox right = new VBox();
			right.setAlignment(Pos.TOP_LEFT);
			right.setSpacing(2);
			right.getChildren().addAll(box, controls);
		
		HBox root = new HBox();
			root.setPadding(new Insets(10, 10, 10, 10));
			root.setSpacing(2);
			root.getChildren().addAll(current, right);
			root.setStyle("-fx-background-color: transparent;");

		play.setOnAction(event -> {
			if (play.getText().equals("Pause")) {
				Main.pause();
				play.setText("Resume");
			} else {
				play.setText("Pause");
				Main.resume();

			}
		});
		load.setOnAction(event -> {
			new LoadFileInterface().start(new Stage());
		});
		reset.setOnAction(event -> {
			play.setText("Start");
			Main.reset();
		});
		close.setOnAction(event -> {
			open = false;
			stage.close();
		});
		Scene scene = new Scene(root);
			scene.setFill(null);
			scene.setOnMousePressed((event) -> {
				x = (int) (stage.getX() - event.getScreenX());
				y = (int) (stage.getY() - event.getScreenY());
			});
			scene.setOnMouseDragged((event) -> {
				stage.setX(event.getScreenX() + x);
				stage.setY(event.getScreenY() + y);
			});
		
		stage.setScene(scene);
			stage.initStyle(StageStyle.TRANSPARENT);
			stage.show();


	}

	public static boolean isOpen() {
		return open;
	}

	public static void flash() {
		DropShadow dropShadow = new DropShadow();
		dropShadow.setColor(Color.LIGHTYELLOW);
		dropShadow.setRadius(30.0);
		dropShadow.setSpread(.6);
		current.setEffect(dropShadow);
		new java.util.Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				current.setEffect(null);

			}

		}, 500);

	}

	public static void display(List<Ability> group) {
		for (int x = 0; x <que.length; x++) {
			Ability ability = group.get(x);
			if(ability!=null)
			loadIcon(que[x], ability.getIcon());
			else
			loadIcon(que[x], "");
		}
	}

	public static void displayTimers(List<String> group) {
		for (int x = 0; x <queTimes.length; x++) {
			String s = group.get(x);
			setTime(queTimes[x],s);
		}
	}
	private static void setTime(Label l,String s){
		Platform.runLater(()->{
			l.setText(s);
			if(Timer.getMilliFromSecs(s)<500){
				l.setTextFill(Color.RED);
			}else{
				l.setTextFill(Color.BLACK);
			}
		});
	}

	private static void loadIcon(ImageView v, String s) {
		File f = new File(s);
		if (f.isFile() && s.contains(".png")) {
			Image i = new Image("file:" + s);
			v.setImage(i);
			
		} else {
		//	System.err.println("error loading file: "+s);
			v.setImage(new Image("file:icons/empty.png"));
		}
			
	}

	public static void main(String[] args) {
		new Thread(()->{
			Main.main();
		}).start();
		launch();

	}

}
