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
	
	// standard get method
    public List<E> getValues () {
        return sharedElements;
    }
	
	public List<E> getImmutableValues () {
        // can't trust the outside world
        reset();
        return Collections.unmodifiableList(getValues());
    }
	
    // accept lambda function, do not reveal collection
    public void apply (Consumer<E> action) {
        // can't trust the outside world
        reset();
        sharedElements.forEach(action);
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
		reset();
		return elements.iterator();
	}
	
	private void reset() {
		sharedElements = new ArrayList<>(elements); 
	}
	
}
