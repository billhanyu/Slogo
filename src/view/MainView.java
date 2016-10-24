package view;


import controller.Controller;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import view.canvas.Canvas;
import view.canvas.ToroidalCanvas;

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
		editor = new Editor(controller, 1000,0);
		
		VBox lefts = new VBox();
		console = new Console(controller, 300,255);
		UserControls controls = new UserControls(controller, 300, 200);
		lefts.getChildren().addAll(controls.getUI(), console.getUI());
				
		environmentView = new EnvironmentView(controller, 200,500);
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
	
	public void showHelpPage(){
		Stage helpPage = new Stage();
        Scene scene = new Scene(new Group());
        VBox root = new VBox();     
        final WebView browser = new WebView();
        final WebEngine webEngine = browser.getEngine();
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(browser); 
        scrollPane.setFitToWidth(true);;
        scrollPane.setFitToHeight(true);
        VBox.setVgrow(scrollPane, Priority.ALWAYS);
		String url = MainView.class.getResource(this.controller.getValueReader().getLabel("HelpPagePath")).toExternalForm();
        webEngine.load(url);
        root.getChildren().addAll(scrollPane);
        scene.setRoot(root);
        helpPage.setScene(scene);
        helpPage.show();
	}
	
}
