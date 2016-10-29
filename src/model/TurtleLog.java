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
	
	public int size(){
		return states.size();
	}
	public void append(ActorState state) {
		states.add(state);
	}

	@Override
	public Iterator<ActorState> iterator() {
		return states.iterator();
	}
	
	public void didRender() {
		ActorState last = states.get(states.size()-1);
		states.clear();
		states.add(0, last);
	}
	
	public void noRender(){
		states.remove(states.size()-1);
	}
	
	public ActorState peekLast() {
		return states.get(states.size() - 1);
	}
	
}
