package model.executable.stdCommand.math;

import java.util.List;

import exception.SyntacticErrorException;
import model.Executable;
import model.TurtleLog;
import model.executable.StandardCommand;

public class Product extends StandardCommand {

	public Product(List<Executable> argv) throws SyntacticErrorException {
		super(argv);
	}

	@Override
	public double execute(TurtleLog log) throws SyntacticErrorException {
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
