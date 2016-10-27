package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

import model.token.Token;

/**
 * @author billyu
 * @author Charles Xu
 * Environment abstract class
 * extended by GlobalVars, UserCommands and CommandHistory
 * encapsulation code borrowed from prof Duvall
 * @param <E>
 */

public abstract class Environment<E extends Executable> implements Iterable<E>{
	
	private List<E> elements;
	private List<E> sharedElements;
	
	public Environment() {
		elements = new ArrayList<E>();
	}
	
	public List<E> getImmutableValues () {
        // can't trust the outside world
        export();
        return Collections.unmodifiableList(sharedElements);
    }
	
	protected List<E> getValues() {
		return elements;
	}
	
    // accept lambda function, do not reveal collection
    public void apply (Consumer<E> action) {
        // can't trust the outside world
        elements.forEach(action);
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
	
	public void remove(E e) {
		elements.remove(e);
	}

	@Override
	public Iterator<E> iterator() {
		export();
		return sharedElements.iterator();
	}
	
	private void export() {
		sharedElements = new ArrayList<>(elements); 
	}
	
	public void addAll(Environment<E> env) {
		elements.addAll(env.getImmutableValues());
	}
}
