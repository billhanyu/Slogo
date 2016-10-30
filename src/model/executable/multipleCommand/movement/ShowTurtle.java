package model.executable.multipleCommand.movement;

import java.util.List;

import exception.SyntacticErrorException;
import model.ActorState;
import model.Executable;
import model.TurtleLog;
import model.TurtleState;
import model.executable.MultipleCommand;

public class ShowTurtle extends MultipleCommand{
	
	public ShowTurtle(List<Executable> argv)
			throws SyntacticErrorException {
		super(argv);
	}

	@Override
	public double execute(TurtleLog log)
			throws SyntacticErrorException {
		ActorState prev = log.peekLast();
		delta = new TurtleState();
		prev.duplicateOnto(delta);
		delta.setVisible(true);
		log.append(delta);
		return 1;
	}

	@Override
	public String getName() {
		return "showturtle";
	}
}

