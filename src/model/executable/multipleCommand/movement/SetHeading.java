package model.executable.multipleCommand.movement;

import java.util.List;

import exception.SyntacticErrorException;
import model.ActorState;
import model.Executable;
import model.TurtleLog;
import model.TurtleState;
import model.executable.MultipleCommand;

public class SetHeading extends MultipleCommand{
	
	public SetHeading(List<Executable> argv)
			throws SyntacticErrorException {
		super(argv);
	}

	@Override
	public double execute(TurtleLog log)
			throws SyntacticErrorException {
		double angle = this.getArgs().get(0).execute(this.getLogHolder());
		ActorState prev = log.peekLast();
		delta = new TurtleState();
		prev.duplicateOnto(delta);
		delta.setDirection(angle);
		log.append(delta);
		return angle - prev.getHeading();
	}

	@Override
	public String getName() {
		return "setheading";
	}
}
