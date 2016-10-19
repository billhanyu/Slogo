package view;

import controller.Controller;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainView {
	
	private Stage stage;
	private Scene mainScene;
	private Controller controller;
	private Canvas canvas;
	private Editor editor;
	private Console console;
	private EnvironmentView environmentView;
	
	public MainView(Controller controller) {
		this.controller = controller;
		init();
	}

	public void init() {
		stage = new Stage();
		stage.setTitle(this.controller.getValueReader().getLabel("Title"));
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
		canvas = new Canvas(controller, 0,0);
		editor = new Editor(controller, 1000,0);
		
		VBox lefts = new VBox();
		console = new Console(controller, 300,250);
		UserControls controls = new UserControls(controller, 300, 200);
		lefts.getChildren().addAll(console.getUI(), controls.getUI());
				
		environmentView = new EnvironmentView(controller, 200,500);
		root.setCenter(canvas.getUI());
		root.setBottom(editor.getUI());
		root.setLeft(lefts);
		root.setRight(environmentView.getUI());
		return scn;
	}
	
}
