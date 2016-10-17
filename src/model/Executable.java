package model;

import exception.SyntacticErrorException;
import exception.UseBeforeDefineException;

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
	 * @throws UseBeforeDefineException 
	 * @throws SyntacticErrorException 
	 */
	double execute(TurtleLog log) throws SyntacticErrorException;
	
	/**
	 * Return the name of this Executable
	 * @return
	 */
	String getName();
}
