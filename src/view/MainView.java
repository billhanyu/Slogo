package view;

import controller.Controller;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainView {
	
	private Stage stage;
	private Scene mainScene;
	private Controller controller;
	private static final String TITLE = "SLOGO";
	
	public MainView(Controller controller) {
		this.controller = controller;
		init();
	}

	public void init() {
		stage = new Stage();
		stage.setTitle(TITLE);
		mainScene = initScene();
		stage.setScene(mainScene);
		stage.show();
	}
	
	private Scene initScene() {
		BorderPane root = new BorderPane();
		Scene scn = new Scene(root);
		Canvas canvas = new Canvas(controller, 0,0,0,0);
		Editor editor = new Editor(controller, 0,0,0,0);
		root.setCenter(canvas.getUI());
		root.setBottom(editor.getUI());
		return scn;
	}
	
}
