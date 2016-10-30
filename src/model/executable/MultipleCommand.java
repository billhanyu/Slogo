package model.executable;

import java.util.List;

import exception.SyntacticErrorException;
import model.Executable;
import model.LogHolder;
import model.TurtleLog;

public abstract class MultipleCommand extends StandardCommand {
	
	private LogHolder logHolder;

	public MultipleCommand(List<Executable> argv) throws SyntacticErrorException {
		super(argv);
	}

	@Override
	public double execute(LogHolder log) throws SyntacticErrorException {
		logHolder = log;
		double result = 0;
		for (TurtleLog active : log.getActiveLogs()) {
			result = execute(active);
		}
		return result;
	}

	public abstract double execute(TurtleLog log) throws SyntacticErrorException;

	protected LogHolder getLogHolder() {
		return logHolder;
	}
	
}
