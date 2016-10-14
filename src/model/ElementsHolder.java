package model;

import java.util.Collection;

public interface ElementsHolder<E> {
	
	void add(E e);
	Collection<E> getAll();
	void remove(E e);
	
}
