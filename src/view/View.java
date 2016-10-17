package view;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;

public abstract class View implements Displayable {
	
	private Group root;
	private Controller controller;
	private double width;
	private double height;
	
	public View(Controller controller, double width, double height) {
		this.controller = controller;
		this.width = width;
		this.height = height;
		root = new Group();
	}
	
	public Node getUI() {
		return root;
	}
	
	protected Controller getController() {
		return controller;
	}
	
	protected double getWidth() {
		return width;
	}
	
	protected double getHeight() {
		return height;
	}
	
	protected Group getRoot() {
		return root;
	}
	
	protected Button makeButton(String text, EventHandler<ActionEvent> action) {
		Button btn = new Button(text);
		btn.setOnAction(action);
		return btn;
	}
}
