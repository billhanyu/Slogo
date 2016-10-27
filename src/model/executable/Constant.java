package model.executable;

import model.Executable;
import model.TurtleLog;
import model.token.Token;

public class Constant implements Executable {
	
	private String name;
	private double value;
	
	public Constant(Token token) {
		this(null, Double.parseDouble(token.toString()));
	}
	
	public Constant(String name) {
		this(name, 0);
	}
	
	public Constant(double value) {
		this(null, value);
	}
	
	public Constant(String name, double value) {
		this.name = name;
		this.value = value;
	}
	
	@Override
	public String getName() {
		return name;
	}

	/**
	 * No effect on log
	 */
	@Override
	public double execute(TurtleLog log) {
		return value;
	}

	@Override
	public void setName(String newName) {
		name = newName;
	}
	
	public void setValue(double val) {
		this.value = val;
	}
}
