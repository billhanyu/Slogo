package model.executable.stdCommand.movement;

import java.util.List;

import exception.SyntacticErrorException;
import model.ActorState;
import model.Executable;
import model.TurtleLog;
import model.TurtleState;
import model.executable.StandardCommand;

public class Towards extends StandardCommand{
	
	public Towards(List<Executable> argv)
			throws SyntacticErrorException {
		super(argv);
	}

	@Override
	public double execute(TurtleLog log)
			throws SyntacticErrorException {
		double x = this.getArgs().get(0).execute(log);
		double y = this.getArgs().get(1).execute(log);
		double newAngle = 0;
		ActorState prev = log.peekLast();
		delta = new TurtleState();
		prev.duplicateOnto(delta);
		if (x - prev.getPositionX() == 0) {
			if (y - prev.getPositionY() < 0) {
				newAngle = -90;
			} else {
				newAngle = 90;
			}
		} else if (y - prev.getPositionY() == 0) {
			if (x - prev.getPositionX() < 0) {
				newAngle = 180;
			} else {
				newAngle = 0;
			}
		} else {
			newAngle = Math.toDegrees(Math.atan2(y - prev.getPositionY(), x - prev.getPositionX()));
		}
		
		delta.setDirection(newAngle);
		log.append(delta);
		return newAngle - prev.getHeading();
	}

	@Override
	public String getName() {
		return "towards";
	}
}