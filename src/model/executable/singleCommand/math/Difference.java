package model.executable.singleCommand.math;

import java.util.List;

import exception.SyntacticErrorException;
import model.Executable;
import model.LogHolder;
import model.executable.StaticCommand;

public class Difference extends StaticCommand {

	public Difference(List<Executable> argv) throws SyntacticErrorException {
		super(argv);
	}

	@Override
	public double execute(LogHolder log) throws SyntacticErrorException {
		return this.getArgs().get(0).execute(log) - this.getArgs().get(1).execute(log);
	}

	@Override
	public String getName() {
		return "difference";
	}
	
}
