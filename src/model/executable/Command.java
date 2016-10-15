package model.executable;

import java.util.List;

import exception.WrongNumberOfArguments;
import model.Executable;
import model.TurtleLog;

public abstract class Command implements Executable{
	
	protected List<Executable> argv;
	
	public Command(List<Executable> argv) {
		this.argv = argv;
	}
	
	@Override
	public abstract double execute();
	
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
	
	/**
	 * Return the expected number of arguments for this command to run
	 * @return
	 */
	public abstract int getExpectedNumArgs();
}
