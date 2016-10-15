package model.executable;

import model.Executable;

public class Constant implements Executable {
	
	private double value;
	
	public Constant(double value) {
		this.value = value;
	}

	@Override
	public double execute() {
		return value;
	}
	
	@Override
	public String toString() {
		return value + "";
	}
}
