package model.executable.singleCommand.math;

import java.util.List;

import exception.LogByNonpositiveException;
import exception.SyntacticErrorException;
import model.Executable;
import model.LogHolder;
import model.executable.SingleCommand;
import util.Utils;

public class Log extends SingleCommand {

	public Log(List<Executable> argv) throws SyntacticErrorException {
		super(argv);
	}

	@Override
	public double execute(LogHolder log) throws SyntacticErrorException {
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
