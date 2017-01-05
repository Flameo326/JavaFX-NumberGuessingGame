package Controller;

import java.util.ArrayList;
import java.util.Random;

import Scenes.EndScene;
import Scenes.GameScene;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class GameSceneController {
	
	GameScene game;
	Random rand;
	int lowRange, highRange, userGuess, attempts;
	ArrayList guesses;
	int randomNumber;
	boolean guessIsNum;
	
	public GameSceneController(GameScene game, int attempts, int low, int high){
		this.attempts = attempts;
		lowRange = low;
		highRange = high;
		guesses = new ArrayList();
		rand = new Random();
		this.game = game;
	}
	
	public void chooseRandomNumber(){
		randomNumber = rand.nextInt(highRange-lowRange+1) + lowRange;
	}

	public void isNum(KeyEvent e, Text text){
		TextField tf = (TextField) e.getSource();
		try{
			userGuess = Integer.parseInt(tf.getText());
		}catch(NumberFormatException ex){
			text.setText("Must be a numerical value");
			guessIsNum = false;
			return;
		}
		text.setText(null);
		guessIsNum = true;
	}
	
	public void validateGuess(ActionEvent e, VBox left, VBox right, TextField source, Text guessError){
		if(isValidGuess(source, guessError)){
			if(guess(left, right)){
				EndScene end = new EndScene(true, randomNumber, game.getAttempts());
				end.scene(game.getStage());
			} else if(attempts == 0){
				EndScene end = new EndScene(false, randomNumber, game.getAttempts());
				end.scene(game.getStage());
			} else{
				game.updateAttemptsText();
				guessError.setText(null);
			}
		}
	}
	
	public boolean isValidGuess(TextField source, Text text){
		if(!guessIsNum){
			text.setText("Must be a numerical value");
			return false;
		}
		if(userGuess < lowRange || userGuess > highRange){
			text.setText("That number is not within the valid range.");
			return false;
		}
		if(guesses.contains(userGuess)){
			text.setText("You have already guessed that number. Try again.");
			return false;
		}
		source.requestFocus();
		source.setText(null);
		text.setText(null);		
		return true;
	}
	
	public boolean guess(VBox left, VBox right){
		left.getChildren().add(new Text(String.valueOf(userGuess)));
		guesses.add(userGuess);
		attempts--;
		if(userGuess == randomNumber){
			right.getChildren().add(new Text("Congradulations! That is correct!"));
			return true;
		} else if(userGuess < randomNumber){
			right.getChildren().add(new Text("That guess is a little low"));
		} else{
			right.getChildren().add(new Text("That guess is a little high"));
		} 
		return false;
	}
	
	public int getLowRange(){
		return lowRange;
	}
	public int getHighRange(){
		return highRange;
	}
	public int getAttempts(){
		return attempts;
	}
}
