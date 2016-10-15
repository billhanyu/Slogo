package model;

/**
 * @author billyu
 * @author Charles Xu
 * API for executable commands
 */

public interface Executable {
	
	/**
	 * Execute its procedure and return the result
	 * @return
	 */
	double execute();
	
	/**
	 * Return the name of this Executable
	 * @return
	 */
	String getName();
}
