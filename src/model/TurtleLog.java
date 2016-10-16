package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * An append-only log that records a sequence of ActorState so that
 * frontend could render the effect of input script in order
 * @author billyu
 */

public class TurtleLog implements Iterable<ActorState> {
	private List<ActorState> states;
	
	public TurtleLog() {
		states = new ArrayList<ActorState>();
	}
	
	public void append(ActorState state) {
		states.add(state);
	}

	@Override
	public Iterator<ActorState> iterator() {
		return states.iterator();
	}
	
	public ActorState peekLast() {
		return states.get(states.size() - 1);
	}
}
