package model.executable.singleCommand.math;

import java.util.List;

import exception.SyntacticErrorException;
import model.Executable;
import model.LogHolder;
import model.executable.StaticCommand;

public class Tan extends StaticCommand {

	public Tan(List<Executable> argv) throws SyntacticErrorException {
		super(argv);
	}

	@Override
	public double execute(LogHolder log) throws SyntacticErrorException {
		return Math.tan(this.getArgs().get(0).execute(log) / 180 * Math.PI);
	}

	@Override
	public String getName() {
		return "tan";
	}

}
