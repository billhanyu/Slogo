package view;

import controller.Controller;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainView {
	
	private Stage stage;
	private Scene mainScene;
	private Controller controller;
	private Canvas canvas;
	private Editor editor;
	private Console console;
	private EnvironmentView environmentView;
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
	
	public Canvas getCanvas() {
		return canvas;
	}
	
	public Editor getEditor() {
		return editor;
	}
	
	public Console getConsole() {
		return console;
	}
	
	private Scene initScene() {
		BorderPane root = new BorderPane();
		Scene scn = new Scene(root);
		canvas = new Canvas(controller, 0,0,0,0);
		editor = new Editor(controller, 0,0,900,0);
		console = new Console(controller, 0,0,0,0);
		environmentView = new EnvironmentView(controller, 0,0,300,700);
		//TODO: set width and height here instead of setting in classes
		root.setCenter(canvas.getUI());
		root.setBottom(editor.getUI());
		root.setLeft(console.getUI());
		root.setRight(environmentView.getUI());
		return scn;
	}
	
}
