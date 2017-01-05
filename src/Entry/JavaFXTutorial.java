package Entry;

import Scenes.StartScene;
import javafx.application.Application;
import javafx.stage.Stage;

public class JavaFXTutorial extends Application{
	StartScene startScene;
	Stage stage;

	public static void main(String[] args) {
		Application.launch( args);
	}
	
	@Override
	public void start(Stage primaryStage){
		stage = primaryStage; // probs dont need
		startScene = new StartScene();
		startScene.scene(primaryStage);
		primaryStage.show();
	}
}

