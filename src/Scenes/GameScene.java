package Scenes;

import Controller.GameSceneController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.BoundingBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameScene extends DisplayScene {
	
	Stage stage;
	GameSceneController controller;
	ScrollPane scroll;
	VBox guesses, distance, bottom;
	Text attemptsText;
	boolean guessIsNum, correct;

	public GameScene(int attempts, int low, int high){
		controller = new GameSceneController(this, attempts, low, high);
		controller.chooseRandomNumber();
	}

	@Override
	public void scene(Stage s) {
		this.stage = s;
		
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root);
		scene.getStylesheets().add("StyleSheets/StyleSheet.css");

		root.setTop(new StackPane(new Text("Guess a Number")));
		
		scroll = new ScrollPane();
		scroll.setViewportBounds(new BoundingBox(0, 0, 50, 50));
		scroll.setMinWidth(250);
		scroll.setMaxWidth(250);
		GridPane centerGrid = new GridPane();
		centerGrid.setAlignment(Pos.TOP_CENTER);
		
		guesses = new VBox();
		guesses.setPadding(new Insets(10));
		guesses.setSpacing(3);
		guesses.setAlignment(Pos.TOP_CENTER);
		guesses.heightProperty().addListener(new ChangeListener(){
			@Override
			public void changed(ObservableValue observale, Object oldValue, Object newValue){
				scroll.setVvalue(1.0);
			}
		}); // Dumb java
		
		distance = new VBox();
		distance.setPadding(new Insets(10));
		distance.setSpacing(3);
		distance.setAlignment(Pos.TOP_CENTER);
		
		centerGrid.addRow(0, guesses, distance);
		scroll.setContent(centerGrid);
		root.setRight(scroll);
		
		Text rangeText = new Text("The range is between " + controller.getLowRange() + " and " + controller.getHighRange() + ".");
		attemptsText = new Text("You have " + controller.getAttempts() + " attempts left.");
		
		TextField guessInput = new TextField();
		Text guessError = new Text();
		Button guessBtn = new Button("Guess");
		guessError.setStyle("-fx-fill: red;");
		guessInput.setMaxWidth(250);
		guessInput.setMinWidth(250);
		guessInput.addEventHandler(KeyEvent.KEY_RELEASED, e -> controller.isNum(e, guessError));
		guessBtn.setOnAction(e -> controller.validateGuess(e, guesses, distance, guessInput, guessError));
		
		VBox centerBox = new VBox(rangeText, attemptsText, guessInput, guessBtn, guessError);
		centerBox.setPadding(new Insets(10));
		centerBox.setSpacing(10);
		centerBox.setAlignment(Pos.CENTER);
		GridPane bottom = new GridPane();
		bottom.add(centerBox, 0, 0);
		bottom.setAlignment(Pos.CENTER);
		root.setLeft(bottom);
		
		stage.setScene(scene);
		stage.centerOnScreen();
	}
	public Stage getStage(){
		return stage;
	}
	public ScrollPane getAttempts(){
		return scroll;
	}
	public void updateAttemptsText(){
		attemptsText.setText("You have " + controller.getAttempts() + " attempts left.");
	}
}
