package model.executable.multipleCommand.movement;

import java.util.List;

import exception.SyntacticErrorException;
import model.ActorState;
import model.Executable;
import model.TurtleLog;
import model.TurtleState;
import model.executable.TurtleCommand;

public class PenUp extends TurtleCommand{
	
	public PenUp(List<Executable> argv)
			throws SyntacticErrorException {
		super(argv);
	}

	@Override
	public double execute(TurtleLog log)
			throws SyntacticErrorException {
		ActorState prev = log.peekLast();
		delta = new TurtleState();
		prev.duplicateOnto(delta);
		delta.getPen().setDown(false);
		log.append(delta);
		return 0;
	}

	@Override
	public String getName() {
		return "penup";
	}
}
