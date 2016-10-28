package model.executable;

import java.util.List;

import exception.SyntacticErrorException;
import model.Executable;
import model.TurtleLog;

public abstract class Command implements Executable{
	
	protected List<Executable> argv;
	protected String name;
	
	public Command(List<Executable> argv)
			throws SyntacticErrorException {
//		Collections.reverse(argv);
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
}
