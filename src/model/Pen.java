package model;

import javafx.scene.paint.Color;

public class Pen implements Cloneable {
	
	public enum PenType {
		Solid, Dashed, Dotted;
	}
	
	private boolean isDown;
	private Color color;
	private double thickness;
	private PenType type;
	public static final Color DEFAULT_PEN_COLOR = Color.BLACK;
	
	public Pen() {
		isDown = true;
		color = DEFAULT_PEN_COLOR;
		thickness = 1;
		type = PenType.Solid;
	}
	
	public boolean isDown() {
		return isDown;
	}
	
	public void setDown(boolean isDown) {
		this.isDown = isDown;
	}
	
	public Color getColor() {
		return color;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public double getThickness() {
		return thickness;
	}
	
	public void setThickness(double thickness) {
		this.thickness = thickness;
	}

	public PenType getType() {
		return type;
	}

	public void setType(PenType type) {
		this.type = type;
	}
	
	@Override
	public Pen clone() {
		Pen result = new Pen();
		result.setColor(color);
		result.setDown(isDown);
		result.setThickness(thickness);
		result.setType(type);
		return result;
	}
	
}
