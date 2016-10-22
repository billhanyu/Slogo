package model.executable;

import model.Executable;
import model.Token;
import model.TurtleLog;

public class Constant implements Executable {
	
	private String name;
	private double value;
	
	public Constant(Token token) {
		this(null, Double.parseDouble(token.toString()));
	}
	
	public Constant(String name) {
		this(name, 0);
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
}
