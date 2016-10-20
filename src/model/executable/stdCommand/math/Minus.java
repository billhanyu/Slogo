package model.executable.stdCommand.math;

import java.util.List;

import exception.SyntacticErrorException;
import model.Executable;
import model.TurtleLog;
import model.executable.StandardCommand;

public class Minus extends StandardCommand {

	public Minus(List<Executable> argv) throws SyntacticErrorException {
		super(argv);
	}

	@Override
	public double execute(TurtleLog log) throws SyntacticErrorException {
		return 0 - this.getArgs().get(0).execute(log);
	}

	@Override
	public String getName() {
		return "minus";
	}

}
