package Controller;

import Scenes.EndScene;
import Scenes.StartScene;

public class EndSceneController {
	
	EndScene end;
	
	public EndSceneController(EndScene e){
		end = e;
	}

	public void restart(){
		StartScene start = new StartScene();
		start.scene(end.getStage());
	}
}
