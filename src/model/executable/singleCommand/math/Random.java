package model.executable.singleCommand.math;

import java.util.List;

import exception.RandomMaxTooSmallException;
import exception.SyntacticErrorException;
import model.Executable;
import model.LogHolder;
import model.executable.StaticCommand;

public class Random extends StaticCommand {

	public Random(List<Executable> argv) throws SyntacticErrorException {
		super(argv);
	}

	@Override
	public double execute(LogHolder log) throws SyntacticErrorException {
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
