package model.executable;

import java.util.List;

import exception.SyntacticErrorException;
import model.Executable;
import model.TurtleLog;
import util.Utils;

public abstract class Command implements Executable{
	
	protected List<Executable> argv;
	protected String name;
	
	public Command(List<Executable> argv)
			throws SyntacticErrorException {
		this.argv = argv;
		validateArgv();
	}
	
	@Override
	public abstract double execute(TurtleLog log)
			throws SyntacticErrorException;
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public void setName(String newName) {
		name = newName;
	}
	
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
