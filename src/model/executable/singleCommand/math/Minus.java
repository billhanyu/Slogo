package model.executable.singleCommand.math;

import java.util.List;

import exception.SyntacticErrorException;
import model.Executable;
import model.LogHolder;
import model.executable.SingleCommand;

public class Minus extends SingleCommand {

	public Minus(List<Executable> argv) throws SyntacticErrorException {
		super(argv);
	}

	@Override
	public double execute(LogHolder log) throws SyntacticErrorException {
		return 0 - this.getArgs().get(0).execute(log);
	}

	@Override
	public String getName() {
		return "minus";
	}

}
