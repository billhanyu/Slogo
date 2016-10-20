package model.executable;

import java.util.Collections;
import java.util.List;

import exception.SyntacticErrorException;
import model.Executable;
import model.TurtleLog;

public abstract class Command implements Executable{
	
	private List<Executable> argv;
	
	public Command(List<Executable> argv)
			throws SyntacticErrorException {
		Collections.reverse(argv);
		this.argv = argv;
		validateArgv();
	}
	
	@Override
	public abstract double execute(TurtleLog log)
			throws SyntacticErrorException;
	
	@Override
	public abstract String getName();
	
	protected abstract void validateArgv()
			throws SyntacticErrorException;
	
	protected List<Executable> getArgs() {
		return argv;
	}
}
