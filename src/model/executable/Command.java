package model.executable;

import model.ActorState;
import model.Executable;
import model.TurtleLog;
import model.TurtleState;

public abstract class Command implements Executable{
	
	protected ActorState delta;

	public double execute() {
		delta = new TurtleState();
		return 0;
	}
	
	/**
	 * The next state appended to log is essentially
	 * 		log.lastEntry + this.delta
	 * Subclasses of Command must define based on their need the semantics
	 * of such superposition as relative to or absolute over the previous state
	 * @param log
	 */
	public abstract void appendToLog(TurtleLog log);
}
