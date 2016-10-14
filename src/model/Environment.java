package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author billyu
 * Environment abstract class
 * extended by GlobalVars, UserCommands and CommandHistory
 * @param <E>
 */

public abstract class Environment<E> {
	
	private List<E> elements;
	
	public Environment() {
		elements = new ArrayList<E>();
	}

	public void add(E e) {
		elements.add(e);
	}
	
	public Collection<E> getAll() {
		return elements;
	}
	
	public void remove(E e) {
		elements.remove(e);
	}
	
}
