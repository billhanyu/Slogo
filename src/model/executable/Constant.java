package model.executable;

import model.Executable;

public class Constant implements Executable {
	
	protected String name;
	protected double value;
	
	public Constant(String name, double value) {
		this.name = name;
		this.value = value;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public double execute() {
		return value;
	}
}
