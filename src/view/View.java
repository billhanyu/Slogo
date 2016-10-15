package view;

import controller.Controller;

public abstract class View implements Displayable {
	
	private Controller controller;
	private double positionX;
	private double positionY;
	private double width;
	private double height;
	
	public View(Controller controller, double x, double y, double width, double height) {
		init(controller, x, y, width, height);
	}
	
	public void init(Controller controller, double x, double y, double width, double height) {
		this.controller = controller;
		this.positionX = x;
		this.positionY = y;
		this.width = width;
		this.height = height;
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
}
