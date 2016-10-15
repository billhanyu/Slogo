package model.executable;

public class Variable extends Constant{
	
	public Variable(String name, double value) {
		super(name, value);
	}
	
	public Variable setValue(double value) {
		this.value = value;
		return this;
	}
}
