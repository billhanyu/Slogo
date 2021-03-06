package view.workspace;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import view.workspace.canvas.MainCanvas;
import view.workspace.environment.EnvironmentView;


/**
 * @author Chalena Scholl
 */
public class WorkspaceView {
	
	private Node sceneRoot;
	private static final int WIDTH = 1000;
	private Scene mainScene;
	private Controller controller;
	private MainCanvas canvas;
	private Editor editor;
	private Console console;
	private EnvironmentView environmentView;
	private MenuView menuView;
	private UserControls controls;
	
	public WorkspaceView(Controller controller) {
		this.controller = controller;
		init();
	}

	public void init() {
		mainScene = initScene();
		mainScene.getStylesheets().add(this.controller.getValueReader().getLabel("StylesheetPath"));
	}
	
	public MainCanvas getCanvas() {
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
		sceneRoot = root;
		Scene scn = new Scene(root);
		
		canvas = new MainCanvas(controller, 700, 500);
		editor = new Editor(controller, WIDTH, 140);
		menuView = new MenuView(controller, WIDTH, 0);
		console = new Console(controller, 255, 200);
		controls = new UserControls(controller, 255, 200);
		environmentView = new EnvironmentView(controller, WIDTH,500);
		
		VBox rights = new VBox();
		rights.getChildren().addAll(environmentView.getUI(), console.getUI(), controls.getUI());
		rights.setFillWidth(true);
		root.setTop(menuView.getUI());
		BorderPane.setMargin(canvas.getUI(), new Insets(10));
		BorderPane.setMargin(rights, new Insets(10));
		root.setBottom(editor.getUI());
		root.setCenter(canvas.getUI());
		root.setRight(rights);
		
		scn.setOnKeyPressed(e -> {
			if (e.isShiftDown() && e.getCode() == KeyCode.ENTER) {
				editor.runScript();
			}
		});
		return scn;
	}
	
	public Node getRoot(){
		return sceneRoot;
	}
}
