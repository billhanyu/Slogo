package model.executable.multipleCommand.movement;

import java.util.List;

import exception.SyntacticErrorException;
import model.ActorState;
import model.Executable;
import model.TurtleLog;
import model.TurtleState;
import model.executable.TurtleCommand;

public class SetXY extends TurtleCommand {
	
	public SetXY(List<Executable> argv)
			throws SyntacticErrorException {
		super(argv);
	}

	@Override
	public double execute(TurtleLog log)
			throws SyntacticErrorException {
		double x = this.getArgs().get(0).execute(this.getLogHolder());
		double y = this.getArgs().get(1).execute(this.getLogHolder());
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
