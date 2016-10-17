package view;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

public class Console extends View {
	
	private TextArea textArea;
	private static final String LABEL = "Console";
	
	public Console(Controller controller, double width, double height) {
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
		textArea.setEditable(false);
		Label label = new Label(LABEL);
		
		VBox all = new VBox();
		all.setPrefWidth(this.getWidth());
		all.setPrefHeight(this.getHeight());
		all.setPadding(new Insets(5,5,5,5));
		all.setSpacing(10);
		all.getChildren().addAll(label, textArea);
		this.getRoot().getChildren().add(all);
	}

}
