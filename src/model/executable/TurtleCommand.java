package model.executable;

import java.util.List;

import exception.SyntacticErrorException;
import model.Executable;
import model.LogHolder;
import model.TurtleLog;

/**
 * @author billyu
 * command type that operates on each turtle
 * loops over each active turtle log to execute
 */
public abstract class TurtleCommand extends StandardCommand {
	
	private LogHolder logHolder;

	public TurtleCommand(List<Executable> argv) throws SyntacticErrorException {
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
