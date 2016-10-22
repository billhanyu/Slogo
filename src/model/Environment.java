package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * @author billyu
 * @author Charles Xu
 * Environment abstract class
 * extended by GlobalVars, UserCommands and CommandHistory
 * @param <E>
 */

public abstract class Environment<E extends Executable> implements Iterable<E>{
	
	private List<E> elements;
	
	public Environment() {
		elements = new ArrayList<E>();
	}
	
	/**
	 * Get an element from list using name as key
	 * Return null if not found
	 * @param name
	 * @return
	 */
	public E get(String name) {
		for (E e : elements){
			if (e.getName().equals(name))
				return e;
		}
		return null;
	}
	
	public E get(Token token) {
		return get(token.toString());
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

	@Override
	public Iterator<E> iterator() {
		return elements.iterator();
	}
	
}
