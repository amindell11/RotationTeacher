package application;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import filemanagers.readers.Ability;
import operation.Main;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 */
public class Overlay extends Application {
	private static boolean open;
	private static ImageView[] que;
	private static ImageView current;
	int x;
	int y;

	@Override
	public void start(Stage stage) {

		HBox controls = new HBox();
		Button play = new Button("Start");
		Button load = new Button("Load");
		Button reset = new Button("Reset");
		Button close = new Button("Close");
		ImageView ico = new ImageView("file:res/gear.png");

		Button settings = new Button("", ico);
		play.setOnAction(event -> {
			if (play.getText().equals("Resume")) {
				Main.resume();
				play.setText("Pause");
			} else {
				Main.pause();
			}
		});
		load.setOnAction(event -> {
			LoadFileInterface.launch();
		});
		reset.setOnAction(event -> {
			Main.reset();
		});
		close.setOnAction(event -> {
			open = false;
			stage.close();
		});
		close.setCancelButton(true);
		play.setDefaultButton(true);
		controls.getChildren().addAll(play, load, reset, close, settings);

		que = new ImageView[5];
		for (int x = 0; x < que.length; x++) {
			que[x] = new ImageView();
			loadIcon(que[x], "");
		}

		current = que[4];
		loadIcon(current, "");
		current.setFitHeight(90);
		current.setFitWidth(90);
		HBox box = new HBox();
		box.setSpacing(2);
		box.getChildren().addAll(que);
		box.getChildren().remove(que[4]);
		HBox root = new HBox();
		root.setPadding(new Insets(10, 10, 10, 10));

		VBox right = new VBox();
		right.setAlignment(Pos.TOP_LEFT);
		right.setSpacing(2);
		right.getChildren().addAll(box, controls);
		root.setSpacing(2);
		root.getChildren().addAll(current, right);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		root.setStyle("-fx-background-color: transparent;");
		scene.setFill(null);
		stage.initStyle(StageStyle.TRANSPARENT);
		scene.setOnMousePressed((event) -> {
			x = (int) (stage.getX() - event.getScreenX());
			y = (int) (stage.getY() - event.getScreenY());
		});
		scene.setOnMouseDragged((event) -> {
			stage.setX(event.getScreenX() + x);
			stage.setY(event.getScreenY() + y);
		});
		stage.show();
		ico.setPreserveRatio(true);
		ico.setFitWidth(15);
		settings.setMaxSize(110, 110);

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
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				current.setEffect(null);

			}

		}, 500);

	}

	public static void display(List<Ability> group) {
		int length=que.length>group.size()?que.length:group.size();
		for (int x = 0; x <length; x++) {
			loadIcon(que[x], group.get(x).getIcon());
		}
	}

	public static void displayTimers(Object queTimes) {
		// TODO Auto-generated method stub

	}

	private static void loadIcon(ImageView v, String s) {
		File f = new File(s);
		System.out.println(s);
		if (f != null && f.isFile() && s.contains(".png")) {
			Image i = new Image("file:" + s);
			v.setImage(i);
		} else {
			System.err.println("error loading file: "+s);
			v.setImage(new Image("file:icons/empty.png"));
		}
	}

	public static void main(String[] args) {
		launch(args);
		List<Ability> s=new ArrayList<Ability>();
		s.add(new Ability(0,"armament1","icons/armament1","",10000));
		Overlay.display(s);
	}

}
