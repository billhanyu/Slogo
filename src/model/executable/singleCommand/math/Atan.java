package model.executable.singleCommand.math;

import java.util.List;

import exception.SyntacticErrorException;
import model.Executable;
import model.LogHolder;
import model.executable.SingleCommand;

public class Atan extends SingleCommand {

	public Atan(List<Executable> argv) throws SyntacticErrorException {
		super(argv);
	}

	@Override
	public double execute(LogHolder log) throws SyntacticErrorException {
		return Math.atan(this.getArgs().get(0).execute(log)) / Math.PI * 180;
	}

	@Override
	public String getName() {
		return "atan";
	}

}
