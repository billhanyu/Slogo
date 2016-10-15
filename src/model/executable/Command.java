package model.executable;

import model.ActorState;
import model.Executable;
import model.TurtleLog;
import model.TurtleState;

public abstract class Command implements Executable{
	
	// TODO(cx15): define a final static name for each subclass
	protected ActorState delta;
	
	public Command() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public double execute() {
		delta = new TurtleState();
		return 0;
	}
	
	@Override
	public abstract String getName();
	
	/**
	 * The next state appended to log is essentially
	 * 		log.lastEntry + this.delta
	 * Subclasses of Command must define based on their need the semantics
	 * of such superposition as relative to or absolute over the previous state
	 * @param log
	 */
	public abstract void appendToLog(TurtleLog log);
}
