package model;

import javafx.scene.paint.Color;

/**
 * @author billyu
 * state of the turtle, including position, pen, direction, visible
 * determines if the previous state to this state needs animation with 'doesAnimate' variable
 */

public class TurtleState implements ActorState {
	
	private double positionX;
	private double positionY;
	private double direction; // in degrees
	private boolean penDown;
	private boolean visible;
	private boolean doesAnimate;
	private Color penColor;

	@Override
	public void setPositionX(double x) {
		positionX = x;
	}

	@Override
	public void setPositionY(double y) {
		positionY = y;
	}

	@Override
	public void setDirection(double degrees) {
		direction = degrees;
	}

	@Override
	public void setPen(boolean isDown) {
		penDown = isDown;
	}

	@Override
	public void setVisible(boolean isVisible) {
		visible = isVisible;
	}

	@Override
	public void setAnimate(boolean animate) {
		doesAnimate = animate;
	}
	
	@Override
	public void setPenColor(Color color) {
		this.penColor = color;
	}

	@Override
	public double getPositionX() {
		return positionX;
	}

	@Override
	public double getPositionY() {
		return positionY;
	}

	@Override
	public double getHeading() {
		return direction;
	}

	@Override
	public boolean isPenDown() {
		return penDown;
	}

	@Override
	public boolean isVisible() {
		return visible;
	}

	@Override
	public boolean doesAnimate() {
		return doesAnimate;
	}
	
	@Override
	public Color getPenColor() {
		return penColor;
	}

	@Override
	public void duplicateOnto(ActorState other) {
		other.setPositionX(this.getPositionX());
		other.setPositionY(this.getPositionY());
		other.setPen(this.isPenDown());
		other.setAnimate(this.doesAnimate());
		other.setDirection(this.getHeading());
		other.setVisible(this.isVisible());
	}
}
