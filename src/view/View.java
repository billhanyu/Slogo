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
	private double positionX;
	private double positionY;
	private double width;
	private double height;
	
	public View(Controller controller, double x, double y, double width, double height) {
		init(controller, x, y, width, height);
		root = new Group();
	}
	
	public void init(Controller controller, double x, double y, double width, double height) {
		this.controller = controller;
		this.positionX = x;
		this.positionY = y;
		this.width = width;
		this.height = height;
	}
	
	public Node getUI() {
		return root;
	}
	
	protected Controller getController() {
		return controller;
	}
	
	protected double getPositionX() {
		return positionX;
	}
	
	protected double getPositionY() {
		return positionY;
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
