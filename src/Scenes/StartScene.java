package Scenes;

import Controller.StartSceneController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StartScene extends DisplayScene{
	
	private Stage stage;
	private StartSceneController control;
	// Text
	private TextField lowRangeInput, highRangeInput, attemptsInput;
	private boolean lowRangeIsNum, highRangeIsNum, attemptsIsNum;
	
	public StartScene(){
		control = new StartSceneController(this);
	}
	
	@Override
	public void scene(Stage stage) {
		this.stage = stage;
		
		BorderPane root = new BorderPane();		
		root.setTop(new StackPane(new Text("Guessing Game")));
		
		Button btn = new Button("Start");
		Text btnError = new Text();
		
		btnError.setStyle("-fx-fill: red;");
		btn.setMaxSize(180, 100);
		btn.setOnMouseClicked(e -> control.start(e, btnError));
		
		VBox vbox = new VBox(btn, btnError);
		vbox.setAlignment(Pos.CENTER);
		vbox.setPadding(new Insets(20));
		root.setBottom(vbox);
		
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.TOP_CENTER);
		grid.setPadding(new Insets(20));
		//grid.setGridLinesVisible(true);
		grid.setHgap(10);
		grid.setVgap(10);
		root.setCenter(grid);
	
		// could I not hardcode the position??? by getting the col and row of variables?
		// yes I should...
		//I could use an hBox heres instead
		lowRangeInput = new TextField();	
		highRangeInput = new TextField();
		Text lowRangeError = new Text();
		Text highRangeError = new Text();
		
		lowRangeError.setStyle("-fx-fill: red;");
		highRangeError.setStyle("-fx-fill: red;");
		lowRangeInput.addEventHandler(KeyEvent.KEY_RELEASED, 
				e -> lowRangeIsNum = control.isNum(e, lowRangeError));
		highRangeInput.addEventHandler(KeyEvent.KEY_RELEASED, e -> highRangeIsNum = control.isNum(e, highRangeError));
		
		grid.addRow(2, new Text("Range: "), lowRangeInput, new Text(" to "), highRangeInput);
		grid.add(lowRangeError, 1, 3);
		grid.add(highRangeError, 3, 3);

		attemptsInput = new TextField();
		Text attemptsError = new Text();
		
		attemptsError.setStyle("-fx-fill: red;");
		attemptsInput.addEventHandler(KeyEvent.KEY_RELEASED, e -> attemptsIsNum = control.isNum(e, attemptsError));
		
		grid.addRow(4, new Text("Attempts"), attemptsInput);
		grid.add(attemptsError, GridPane.getColumnIndex(attemptsInput), GridPane.getRowIndex(attemptsInput)+1);
		
		Scene scene = new Scene(root);
		scene.getStylesheets().add("StyleSheets/StyleSheet.css");
		stage.setScene(scene);
		stage.centerOnScreen();
	}
	
	public Stage getStage(){
		return stage;
	}
	public TextField getLowRangeInput(){
		return lowRangeInput;
	}
	public TextField getHighRangeInput(){
		return highRangeInput;
	}
	public TextField getAttemptsInput(){
		return attemptsInput;
	}
	
	public boolean getLowRangeIsNum(){
		return lowRangeIsNum;
	}
	public boolean getHighRangeIsNum(){
		return highRangeIsNum;
	}
	public boolean getAttemptsIsNum(){
		return attemptsIsNum;
	}
}

// Create an abstract controller class 
// extend some classes form it
// intitialize them with an Scene class
// provide some methods
