package model.executable.stdCommand.math;

import java.util.List;

import exception.RandomMaxTooSmallException;
import exception.SyntacticErrorException;
import model.Executable;
import model.TurtleLog;
import model.executable.StandardCommand;

public class Random extends StandardCommand {

	public Random(List<Executable> argv) throws SyntacticErrorException {
		super(argv);
	}

	@Override
	public double execute(TurtleLog log) throws SyntacticErrorException {
		double max = this.getArgs().get(0).execute(log);
		if (max < 0) {
			throw new RandomMaxTooSmallException();
		}
		return Math.random() * max;
	}

	@Override
	public String getName() {
		return "random";
	}

}
