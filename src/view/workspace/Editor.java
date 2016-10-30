package view.workspace;

import controller.Controller;
import exception.SyntacticErrorException;
import exception.UnrecognizedIdentifierException;
import exception.WrongNumberOfArguments;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class Editor extends View {
	
	private TextArea textArea;
	private Button runButton;
	private Button clearButton;
	
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
	
	public void runScript() {
		try {
			this.getController().runScript(textArea.getText());
		} catch (UnrecognizedIdentifierException e1) {
			this.getController().getMainView().getConsole().appendText(
					this.getLabelReader().getLabel("UnrecognizedIdentifier"), TextType.Error);
		} catch (WrongNumberOfArguments e1) {
			this.getController().getMainView().getConsole().appendText(
					this.getLabelReader().getLabel("WrongNumberOfArguments"), TextType.Error);
		} catch (SyntacticErrorException e1) {
			this.getController().getMainView().getConsole().appendText(
					this.getLabelReader().getLabel("SyntacticError"), TextType.Error);
		}
	}
	
	public void clearText() {
		textArea.clear();
	}
	
	private void init() {
		textArea = new TextArea();
		makeRunButton();
		makeClearButton();
		
		VBox buttons = new VBox();
		buttons.setAlignment(Pos.CENTER_RIGHT);
		buttons.setPrefWidth(100);
		buttons.setSpacing(20);
		buttons.getChildren().addAll(runButton, clearButton);
		
		HBox all = new HBox();
		all.setPadding(new Insets(5,5,5,5));
		all.setPrefWidth(this.getWidth());
		all.setPrefHeight(this.getHeight());
		all.getChildren().addAll(textArea, buttons);
		HBox.setHgrow(textArea, Priority.ALWAYS);
		textArea.setId("editor");
		this.getRoot().getChildren().add(all);
	}

	private void makeClearButton() {
		clearButton = this.makeButton(this.getLabelReader().getLabel("ClearUserInput"), e -> {
			clearText();
		});
		clearButton.setPrefWidth(90);
		clearButton.setPrefHeight(80);
		clearButton.getStyleClass().add("clearButton");
	}

	private void makeRunButton() {
		runButton = this.makeButton(this.getLabelReader().getLabel("RunUserInput"), e -> {
			runScript();
		});
		runButton.setAlignment(Pos.CENTER);
		runButton.setPrefWidth(90);
		runButton.setPrefHeight(80);
		runButton.getStyleClass().add("runButton");
	}

}
