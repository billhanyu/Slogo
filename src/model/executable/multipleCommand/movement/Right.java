package model.executable.multipleCommand.movement;

import java.util.List;

import exception.SyntacticErrorException;
import model.ActorState;
import model.Executable;
import model.TurtleLog;
import model.TurtleState;
import model.executable.MultipleCommand;

public class Right extends MultipleCommand{
	
	public Right(List<Executable> argv)
			throws SyntacticErrorException {
		super(argv);
	}

	@Override
	public double execute(TurtleLog log)
			throws SyntacticErrorException {
		double offsetAngle = this.getArgs().get(0).execute(this.getLogHolder());
		ActorState prev = log.peekLast();
		delta = new TurtleState();
		prev.duplicateOnto(delta);
		delta.setDirection(prev.getHeading() + offsetAngle);
		log.append(delta);
		return offsetAngle;
	}

	@Override
	public String getName() {
		return "right";
	}
}