package view;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class Console extends View {
	
	private TextFlow textFlow;
	private ScrollPane scrollPane;
	
	public Console(Controller controller, double width, double height) {
		super(controller, width, height);
		init();
	}

	public void setText(String text, TextType type) {
		textFlow.getChildren().removeAll();
		appendText(text, type);
	}
	
	public void appendText(String text, TextType type) {
		Text newText = new Text(text + "\n");
		switch (type) {
		case Error:
			newText.setFill(Color.RED);
			break;
		case Plain:
			newText.setFill(Color.BLACK);
			break;
		case Success:
			newText.setFill(Color.GREEN);
			break;
		}
		textFlow.getChildren().add(newText);
		textFlow.requestLayout();
		scrollPane.setVvalue(1.0);
	}
	
	private void init() {
		textFlow = new TextFlow();
		textFlow.setMaxHeight(this.getHeight());
		scrollPane = new ScrollPane(textFlow);
		scrollPane.setFitToWidth(true);
		scrollPane.setStyle("-fx-background: rgb(255,255,255);");
		scrollPane.setPrefHeight(this.getHeight());
		Label label = new Label(this.getLabelReader().getLabel("Console"));
		VBox all = new VBox();
		all.setPrefWidth(this.getWidth());
		all.setPrefHeight(this.getHeight());
		all.getChildren().addAll(label, scrollPane);
		scrollPane.setId("border");
		this.getRoot().getChildren().add(all);
	}

}
