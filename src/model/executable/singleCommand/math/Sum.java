package model.executable.singleCommand.math;

import java.util.List;

import exception.SyntacticErrorException;
import model.Executable;
import model.LogHolder;
import model.executable.StaticCommand;

public class Sum extends StaticCommand {

	public Sum(List<Executable> argv) throws SyntacticErrorException {
		super(argv);
	}

	@Override
	public double execute(LogHolder log) throws SyntacticErrorException {
		double result = 0;
		for (Executable arg: this.getArgs()) {
			result += arg.execute(log);
		}
		return result;
	}

	@Override
	public String getName() {
		return "sum";
	}

}
