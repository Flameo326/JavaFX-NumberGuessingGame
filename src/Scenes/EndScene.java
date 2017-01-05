package Scenes;

import Controller.EndSceneController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class EndScene extends DisplayScene {
	
	Stage stage;
	EndSceneController controller;
	ScrollPane attempts;
	boolean won;
	int choosenNumber;
	
	public EndScene(boolean won, int randomNumber, ScrollPane scroll){
		this.won = won;
		choosenNumber = randomNumber;
		attempts = scroll;
		controller = new EndSceneController(this);
	}

	@Override
	public void scene(Stage s) {
		stage = s;
		
		
		// Maybe I should use a BorderPane
		VBox root = new VBox();
		root.setSpacing(20.0);
		root.setAlignment(Pos.CENTER);
		 
		// I could porobably make this in constructor????
		String text;
		if(won){
			text = "You Won!";
		} else{
			text = "You Lost!";
		}
		root.getChildren().add(new StackPane(new Text(text)));
		
		root.getChildren().add(new Text("The number was " + choosenNumber));
		
		Button playAgain = new Button("Play Again?");
		playAgain.setOnAction(e -> controller.restart());
		root.getChildren().add(playAgain);
		
		root.getChildren().add(new Text("Here are your attempts"));
		root.getChildren().add(attempts);
		
		Scene scene = new Scene(root);
		scene.getStylesheets().add("StyleSheets/StyleSheet.css");
		stage.setScene(scene);
		stage.centerOnScreen();
	}
	
	public Stage getStage(){
		return stage;
	}
}
