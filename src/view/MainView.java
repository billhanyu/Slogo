package view;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainView {
	
	private Stage stage;
	private Scene mainScene;
	
	public MainView() {
		stage = new Stage();
	}

	public void init() {
		mainScene = initScene();
		stage.setScene(mainScene);
		stage.show();
	}
	
	private Scene initScene() {
		return null;
	}
	
}
