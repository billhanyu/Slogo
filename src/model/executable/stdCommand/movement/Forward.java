package model.executable.stdCommand.movement;

import java.util.List;

import model.ActorState;
import model.Executable;
import model.TurtleLog;
import model.executable.StandardCommand;

public class Forward extends StandardCommand{
	
	public static final String NAME = "forward";

	public Forward(List<Executable> argv) {
		super(argv);
	}

	@Override
	public double execute(TurtleLog log) {
		double offset = argv.get(0).execute(log);
		ActorState prev = log.peekLast();
		prev.duplicateOnto(delta);
		delta.setPositionX(offset * Math.cos(prev.getHeading()));
		delta.setPositionY(offset * Math.sin(prev.getHeading()));
		log.append(delta);
		return offset;
	}

	@Override
	public String getName() {
		return NAME;
	}
}
