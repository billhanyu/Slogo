package model.executable;

import java.util.List;

import exception.SyntacticErrorException;
import model.Executable;
import model.LogHolder;
import util.Utils;

/**
 * Common abstractions from both user defined command and SLOGO standard command
 * @author CharlesXu
 */
public abstract class Command implements Executable {
	
	protected List<Executable> argv;
	protected String name;
	
	public Command(List<Executable> argv)
			throws SyntacticErrorException {
		this.argv = argv;
		validateArgv();
	}
	
	@Override
	public abstract double execute(LogHolder log)
			throws SyntacticErrorException;
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public void setName(String newName) {
		name = newName;
	}
	
	/**
	 * validate the list of arguments passed in to this command 
	 * so they each map to the right type
	 * @throws SyntacticErrorException
	 */
	protected abstract void validateArgv()
			throws SyntacticErrorException;
	
	protected List<Executable> getArgs() {
		return argv;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getName()).append(SPACE);
		Utils.appendList(sb, argv, SPACE);
		return sb.toString();
	}
}
