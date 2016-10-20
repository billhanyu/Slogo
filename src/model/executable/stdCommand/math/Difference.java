package model.executable.stdCommand.math;

import java.util.List;

import exception.SyntacticErrorException;
import model.Executable;
import model.TurtleLog;
import model.executable.StandardCommand;

public class Difference extends StandardCommand {

	public Difference(List<Executable> argv) throws SyntacticErrorException {
		super(argv);
	}

	@Override
	public double execute(TurtleLog log) throws SyntacticErrorException {
		return this.getArgs().get(0).execute(log) - this.getArgs().get(1).execute(log);
	}

	@Override
	public String getName() {
		return "difference";
	}
	
}
