package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author billyu
 * Log of TurtleStates for frontend to update
 */

public class TurtleLog implements Iterable<TurtleState> {
	private List<TurtleState> states;
	
	public TurtleLog() {
		states = new ArrayList<TurtleState>();
	}
	
	public void append(TurtleState state) {
		states.add(state);
	}

	@Override
	public Iterator<TurtleState> iterator() {
		return states.iterator();
	}
}
