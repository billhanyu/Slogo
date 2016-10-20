package model.executable.stdCommand.movement;

import java.util.List;

import exception.SyntacticErrorException;
import model.ActorState;
import model.Executable;
import model.TurtleLog;
import model.TurtleState;
import model.executable.StandardCommand;

public class SetXY extends StandardCommand{
	
	public SetXY(List<Executable> argv)
			throws SyntacticErrorException {
		super(argv);
	}

	@Override
	public double execute(TurtleLog log)
			throws SyntacticErrorException {
		double x = argv.get(0).execute(log);
		double y = argv.get(1).execute(log);
		ActorState prev = log.peekLast();
		delta = new TurtleState();
		prev.duplicateOnto(delta);
		delta.setPositionX(x);
		delta.setPositionY(y);
		log.append(delta);
		double xDist = x - prev.getPositionX();
		double yDist = y - prev.getPositionY();
		return Math.sqrt((xDist*xDist) + (yDist*yDist));
	}

	@Override
	public String getName() {
		return "setxy";
	}
}
