package view.workspace;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import view.floating.AbstractPublisher;

public abstract class View extends AbstractPublisher implements Displayable {
	
	private Group root;
	private Controller controller;
	private double width;
	private double height;
	private DisplayLabelReader labelReader;
	
	public View(Controller controller, double width, double height) {
		this.controller = controller;
		this.width = width;
		this.height = height;
		this.labelReader = this.controller.getValueReader();
		root = new Group();
	}
	
	public Node getUI() {
		return root;
	}
	
	protected Controller getController() {
		return controller;
	}
	
	protected DisplayLabelReader getLabelReader(){
		return this.labelReader;
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
