package model.executable.multipleCommand.movement;

import java.util.List;

import exception.SyntacticErrorException;
import model.ActorState;
import model.Executable;
import model.TurtleLog;
import model.TurtleState;
import model.executable.TurtleCommand;

public class Forward extends TurtleCommand{
	
	public Forward(List<Executable> argv)
			throws SyntacticErrorException {
		super(argv);
	}

	@Override
	public double execute(TurtleLog log)
			throws SyntacticErrorException {
		double offset = this.getArgs().get(0).execute(this.getLogHolder());
		ActorState prev = log.peekLast();
		delta = new TurtleState();
		prev.duplicateOnto(delta);
		delta.setPositionX(prev.getPositionX() + 
				offset * Math.sin(prev.getHeading() / 180 * Math.PI));
		delta.setPositionY(prev.getPositionY() - 
				offset * Math.cos(prev.getHeading() / 180 * Math.PI));
		log.append(delta);
		return offset;
	}

	@Override
	public String getName() {
		return "forward";
	}
}
