package model.executable.stdCommand.bool;

import java.util.List;

import exception.SyntacticErrorException;
import model.Executable;
import model.TurtleLog;
import model.executable.StandardCommand;
import util.Utils;

public class And extends StandardCommand {

	public And(List<Executable> argv) throws SyntacticErrorException {
		super(argv);
	}

	@Override
	public double execute(TurtleLog log) throws SyntacticErrorException {
		double result0 = this.getArgs().get(0).execute(log);
		double result1 = this.getArgs().get(1).execute(log);
		if (!Utils.doubleEqual(result0, 0) && !Utils.doubleEqual(result1, 0)) {
			return 1;
		}
		return 0;
	}

	@Override
	public String getName() {
		return "and";
	}

}
