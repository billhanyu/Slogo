package model.executable.stdCommand.movement;

import java.util.List;

import exception.SyntacticErrorException;
import model.ActorState;
import model.Executable;
import model.TurtleLog;
import model.TurtleState;
import model.executable.StandardCommand;

public class Left extends StandardCommand{
	
	public Left(List<Executable> argv)
			throws SyntacticErrorException {
		super(argv);
	}

	@Override
	public double execute(TurtleLog log)
			throws SyntacticErrorException {
		double offsetAngle = argv.get(0).execute(log);
		ActorState prev = log.peekLast();
		delta = new TurtleState();
		prev.duplicateOnto(delta);
		delta.setDirection(prev.getHeading() + offsetAngle);
		log.append(delta);
		return offsetAngle;
	}

	@Override
	public String getName() {
		return "left";
	}
}
