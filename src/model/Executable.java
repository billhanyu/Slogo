package model;

/**
 * @author billyu
 * @author Charles Xu
 * API for executable commands
 */

public interface Executable {
	
	/**
	 * Execute its procedure and return the result
	 * The effect of the execution depends on the previous state
	 * thus requires a log as argument
	 * @return
	 */
	double execute(TurtleLog log);
	
	/**
	 * Return the name of this Executable
	 * @return
	 */
	String getName();
}
