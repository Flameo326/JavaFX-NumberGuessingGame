package Controller;

import Scenes.GameScene;
import Scenes.StartScene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class StartSceneController extends Controller{
	
	StartScene scene;

	public StartSceneController(StartScene scene){
		this.scene = scene;
	}
	
	public void start(MouseEvent e, Text text){
		if( !scene.getLowRangeIsNum() || !scene.getHighRangeIsNum() || !scene.getAttemptsIsNum()){
			text.setText("Please give every text field a correct value.");
			return;
		}
		int lowRange = Integer.parseInt(scene.getLowRangeInput().getText());
		int highRange = Integer.parseInt(scene.getHighRangeInput().getText());
		int attempts = Integer.parseInt(scene.getAttemptsInput().getText());
		if(attempts < 1){
			text.setText("Attempts must be a positive number.");
			return;
		}
		if(lowRange >= highRange){
			text.setText("Range must go from a low value to a high value.");
			return;
		}
		
		GameScene game = new GameScene(attempts, lowRange, highRange);
		game.scene(scene.getStage());
	}
	
	// should I have a handler class for type and call different methods?
	// or does this actually work?
	// Can I make this class seperate from the Applicatio  class
	// Should I pass an instance so I can get the nodes and manipulat ethem?
	
	public boolean isNum(KeyEvent e, Text text){
		TextField tf = (TextField) e.getSource();
		try{
			Integer.parseInt(tf.getText());
		}catch(NumberFormatException ex){
			text.setText("Must be a numerical value.");
			return false;
		}
		text.setText(null);
		return true;
	}
}
