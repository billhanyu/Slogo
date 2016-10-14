package model;

public class Variable<E> {
	
	private String name;
	private E value;
	
	public Variable(String name, E value) {
		this.name = name;
		this.value = value;
	}
	
	public String getName() {
		return name;
	}
	
	public E getValue() {
		return value;
	}

}
