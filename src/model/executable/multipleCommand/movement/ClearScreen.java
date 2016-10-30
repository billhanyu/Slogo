package model.executable.multipleCommand.movement;

import java.util.List;

import exception.SyntacticErrorException;
import model.ActorState;
import model.Executable;
import model.TurtleLog;
import model.TurtleState;
import model.executable.MultipleCommand;

public class ClearScreen extends MultipleCommand{
	
	public ClearScreen(List<Executable> argv)
			throws SyntacticErrorException {
		super(argv);
	}

	@Override
	public double execute(TurtleLog log)
			throws SyntacticErrorException {
		ActorState prev = log.peekLast();
		delta = new TurtleState();
		prev.duplicateOnto(delta);
		delta.setPositionX(0.0);
		delta.setPositionY(0.0);
		delta.setDirection(0);
		delta.setClearScreen(true);
		log.append(delta);
		double xDist = prev.getPositionX();
		double yDist = prev.getPositionY();
		return Math.sqrt((xDist*xDist) + (yDist*yDist));
	}

	@Override
	public String getName() {
		return "clearscreen";
	}
}
