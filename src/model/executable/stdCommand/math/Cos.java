package model.executable.stdCommand.math;

import java.util.List;

import exception.SyntacticErrorException;
import model.Executable;
import model.TurtleLog;
import model.executable.StandardCommand;

public class Cos extends StandardCommand {

	public Cos(List<Executable> argv) throws SyntacticErrorException {
		super(argv);
	}

	@Override
	public double execute(TurtleLog log) throws SyntacticErrorException {
		return Math.cos(this.getArgs().get(0).execute(log) / 180 * Math.PI);
	}

	@Override
	public String getName() {
		return "cos";
	}

}
