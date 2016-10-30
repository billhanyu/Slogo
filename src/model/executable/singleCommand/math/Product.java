package model.executable.singleCommand.math;

import java.util.List;

import exception.SyntacticErrorException;
import model.Executable;
import model.LogHolder;
import model.executable.SingleCommand;

public class Product extends SingleCommand {

	public Product(List<Executable> argv) throws SyntacticErrorException {
		super(argv);
	}

	@Override
	public double execute(LogHolder log) throws SyntacticErrorException {
		double result = 1;
		for (Executable arg: this.getArgs()) {
			result *= arg.execute(log);
		}
		return result;
	}

	@Override
	public String getName() {
		return "product";
	}

}
