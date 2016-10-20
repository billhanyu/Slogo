package model.executable.stdCommand.math;

import java.util.List;

import exception.SyntacticErrorException;
import model.Executable;
import model.TurtleLog;
import model.executable.StandardCommand;

public class Atan extends StandardCommand {

	public Atan(List<Executable> argv) throws SyntacticErrorException {
		super(argv);
	}

	@Override
	public double execute(TurtleLog log) throws SyntacticErrorException {
		return Math.atan(this.getArgs().get(0).execute(log)) / Math.PI * 180;
	}

	@Override
	public String getName() {
		return "atan";
	}

}
