package view;


import controller.Controller;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.canvas.Canvas;

public class MainView {
	
	private static final int WIDTH = 1000;
	private Stage stage;
	private Scene mainScene;
	private Controller controller;
	private Canvas canvas;
	private Editor editor;
	private Console console;
	private EnvironmentView environmentView;
	private MenuView menuView;
	
	public MainView(Controller controller) {
		this.controller = controller;
		init();
	}

	public void init() {
		stage = new Stage();
		stage.setWidth(WIDTH);
		stage.setResizable(false);
		stage.setTitle(this.controller.getValueReader().getLabel("Title"));
		mainScene = initScene();
		mainScene.getStylesheets().add(this.controller.getValueReader().getLabel("StylesheetPath"));
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
	
	public EnvironmentView getEnvironmentView() {
		return environmentView;
	}
	
	private Scene initScene() {
		BorderPane root = new BorderPane();
		Scene scn = new Scene(root);
		canvas = new Canvas(controller, 0,0);
		editor = new Editor(controller, WIDTH,0);
		menuView = new MenuView(controller, WIDTH, 0);
		
		VBox lefts = new VBox();
		console = new Console(controller, 300,255);
		UserControls controls = new UserControls(controller, 300, 200);
		lefts.getChildren().addAll(controls.getUI(), console.getUI());
				
		environmentView = new EnvironmentView(controller, 180,500);
		root.setTop(menuView.getUI());
		root.setCenter(canvas.getUI());
		root.setBottom(editor.getUI());
		root.setLeft(lefts);
		root.setRight(environmentView.getUI());
		
		scn.setOnKeyPressed(e -> {
			if (e.isShiftDown() && e.getCode() == KeyCode.ENTER) {
				editor.runScript();
			}
		});
		return scn;
	}
	
}
