package model.executable.stdCommand.movement;

import java.util.List;

import exception.SyntacticErrorException;
import model.ActorState;
import model.Executable;
import model.TurtleLog;
import model.TurtleState;
import model.executable.StandardCommand;

public class Towards extends StandardCommand{
	private static final double UP = 0;
	private static final double DOWN = 180;
	private static final double RIGHT = 90;
	private static final double LEFT = -90;
	
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
			if (y - prev.getPositionY() > 0) {
				newAngle = DOWN;
			} else {
				newAngle = UP;
			}
		} else if (y - prev.getPositionY() == 0) {
			if (x - prev.getPositionX() < 0) {
				newAngle = LEFT;
			} else {
				newAngle = RIGHT;
			}
		} else {
			newAngle = 90 + Math.toDegrees(Math.atan2(y - prev.getPositionY(), x - prev.getPositionX()));
			//add extra 90deg to shift from Cartesian angle conventions to JavaFX angle conventions
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