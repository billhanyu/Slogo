package view;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Editor extends View {
	
	private TextArea textArea;
	private Button runButton;
	private Button clearButton;
	private static final String RUN_TEXT = "Run";
	private static final String CLEAR_TEXT = "Clear";
	
	public Editor(Controller controller, double width, double height) {
		super(controller, width, height);
		init();
	}

	public void setText(String text) {
		textArea.setText(text);
	}
	
	public void appendText(String text) {
		textArea.appendText(text);
	}
	
	private void init() {
		textArea = new TextArea();
		runButton = this.makeButton(RUN_TEXT, e -> {
			this.getController().runScript(textArea.getText());
		});
		clearButton = this.makeButton(CLEAR_TEXT, e -> {
			textArea.clear();
		});
		runButton.setPrefWidth(70);
		clearButton.setPrefWidth(70);
		
		VBox buttons = new VBox();
		buttons.setAlignment(Pos.CENTER);
		buttons.setPrefWidth(100);
		buttons.setSpacing(50);
		buttons.getChildren().addAll(runButton, clearButton);
		
		HBox all = new HBox();
		all.setPadding(new Insets(5,5,5,5));
		all.setPrefWidth(this.getWidth());
		all.getChildren().addAll(textArea, buttons);
		this.getRoot().getChildren().add(all);
	}

}
