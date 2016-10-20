package model.executable.stdCommand.math;

import java.util.List;

import exception.LogByNonpositiveException;
import exception.SyntacticErrorException;
import model.Executable;
import model.TurtleLog;
import model.executable.StandardCommand;
import util.Utils;

public class Log extends StandardCommand {

	public Log(List<Executable> argv) throws SyntacticErrorException {
		super(argv);
	}

	@Override
	public double execute(TurtleLog log) throws SyntacticErrorException {
		double result = this.getArgs().get(0).execute(log);
		if (result < 0 || Utils.doubleEqual(result, 0)) {
			throw new LogByNonpositiveException();
		}
		return Math.log(result);
	}

	@Override
	public String getName() {
		return "log";
	}

}
