package model.executable;

import model.Executable;

public class Variable implements Executable{
	
	private String name;
	private double value;
	
	public Variable(String name, double value) {
		this.name = name;
		this.value = value;
	}
	
	public String getName() {
		return name;
	}

	@Override
	public double execute() {
		return value;
	}
}
