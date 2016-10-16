package model.executable;

import model.Executable;
import model.TurtleLog;

public class Constant implements Executable {
	
	protected String name;
	protected double value;
	
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
}
