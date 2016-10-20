package model.executable.stdCommand.movement;

import java.util.List;

import exception.SyntacticErrorException;
import model.ActorState;
import model.Executable;
import model.TurtleLog;
import model.TurtleState;
import model.executable.StandardCommand;

public class Forward extends StandardCommand{
	
	public Forward(List<Executable> argv)
			throws SyntacticErrorException {
		super(argv);
	}

	@Override
	public double execute(TurtleLog log)
			throws SyntacticErrorException {
		double offset = this.getArgs().get(0).execute(log);
		ActorState prev = log.peekLast();
		delta = new TurtleState();
		prev.duplicateOnto(delta);
		delta.setPositionX(prev.getPositionX() + offset * Math.cos(prev.getHeading()));
		delta.setPositionY(prev.getPositionY() + offset * Math.sin(prev.getHeading()));
		log.append(delta);
		return offset;
	}

	@Override
	public String getName() {
		return "forward";
	}
}
