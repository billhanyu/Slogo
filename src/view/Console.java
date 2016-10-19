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
	}
	
	private void init() {
		textFlow = new TextFlow();
		textFlow.setMaxHeight(this.getHeight());
		ScrollPane scrollPane = new ScrollPane(textFlow);
		scrollPane.setFitToWidth(true);
		scrollPane.setStyle("-fx-background: rgb(255,255,255);");
		scrollPane.setPrefHeight(this.getHeight());
		Label label = new Label(this.getController().getValueReader().getValue("ConsoleLabel"));
		VBox all = new VBox();
		all.setPrefWidth(this.getWidth());
		all.setPrefHeight(this.getHeight());
		all.setPadding(new Insets(5,5,5,5));
		all.setSpacing(10);
		all.getChildren().addAll(label, scrollPane);
		this.getRoot().getChildren().add(all);
	}

}
