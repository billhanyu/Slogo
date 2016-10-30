package model.executable.singleCommand.math;

import java.util.List;

import exception.DivideByZeroException;
import exception.SyntacticErrorException;
import model.Executable;
import model.LogHolder;
import model.executable.SingleCommand;
import util.Utils;

public class Quotient extends SingleCommand {

	public Quotient(List<Executable> argv) throws SyntacticErrorException {
		super(argv);
	}

	@Override
	public double execute(LogHolder log) throws SyntacticErrorException {
		double numerator = this.getArgs().get(0).execute(log);
		double denominator = this.getArgs().get(1).execute(log);
		if (Utils.doubleEqual(denominator, 0)) {
			throw new DivideByZeroException();
		}
		return numerator / denominator;
	}

	@Override
	public String getName() {
		return "quotient";
	}

}
