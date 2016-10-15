package model.executable.stdCommand;

import java.util.List;

import model.ActorState;
import model.Executable;
import model.TurtleLog;
import model.executable.StandardCommand;

public class Forward extends StandardCommand{
	
	public static final int NUM_ARGUMENTS = 1;
	public static final String NAME = "forward";

	public Forward(List<Executable> argv) {
		super(argv);
	}

	@Override
	public double execute() {
		double ret = argv.get(0).execute();
		delta.setPositionX(ret);
		return ret;
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public void appendToLog(TurtleLog log) {
		double offset = delta.getPositionX();
		ActorState prev = log.peekLast();
		prev.duplicateOnto(delta);
		delta.setPositionX(offset * Math.cos(prev.getHeading()));
		delta.setPositionY(offset * Math.sin(prev.getHeading()));
		log.append(delta);
	}

	@Override
	public int getExpectedNumArgs() {
		return NUM_ARGUMENTS;
	}
}
