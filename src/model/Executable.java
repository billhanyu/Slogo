package model;

import exception.SyntacticErrorException;
import exception.UseBeforeDefineException;

/**
 * @author billyu
 * @author Charles Xu
 * API for executable commands
 */

public interface Executable {
	
	public static final String SPACE = " ";
	
	/**
	 * Execute its procedure and return the result
	 * The effect of the execution depends on the previous state
	 * thus requires a log as argument
	 * @return
	 * @throws UseBeforeDefineException 
	 * @throws SyntacticErrorException 
	 */
	double execute(LogHolder log) throws SyntacticErrorException;
	
	/**
	 * Return the name of this Executable
	 * @return
	 */
	String getName();
	
	/**
	 * Set the name of this Executable
	 */
	void setName(String newName);
}
