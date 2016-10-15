package view;

import controller.Controller;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainView {
	
	private Stage stage;
	private Scene mainScene;
	private Controller controller;
	
	public MainView(Controller controller) {
		this.controller = controller;
		init();
	}

	public void init() {
		stage = new Stage();
		stage.setTitle("SLOGO");
		mainScene = initScene();
		stage.setScene(mainScene);
		stage.show();
	}
	
	private Scene initScene() {
		BorderPane root = new BorderPane();
		Scene scn = new Scene(root);
		Canvas canvas = new Canvas(controller, 0,0,0,0);
		root.setCenter(canvas.getUI());
		return scn;
	}
	
}
