package model;

import java.util.ArrayList;
import java.util.List;

public class StackFrame {
	
	private List<Executable> frame;
	
	public StackFrame() {
		frame = new ArrayList<>();
	}
	
	/**
	 * Put an Executable onto frame. If old entry with the same name exists,
	 * overwrite that entry 
	 * @param e
	 * @return
	 */
	public StackFrame put(Executable e) {
		Executable entry = get(e.getName());
		if (entry != null) {
			frame.remove(entry);
		}
		frame.add(e);
		return this;
	}
	
	/**
	 * Find an Executable from the StackFrame using name as key
	 * return null if not found
	 * @param name
	 * @return
	 */
	public Executable get(String name) {
		for (Executable e : frame) {
			if (e.getName().equals(name)) {
				return e;
			}
		}
		return null;
	}
}
