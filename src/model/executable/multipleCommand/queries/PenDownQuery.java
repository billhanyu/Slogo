package model.executable.multipleCommand.queries;

import java.util.List;

import exception.SyntacticErrorException;
import model.ActorState;
import model.Executable;
import model.TurtleLog;
import model.executable.MultipleCommand;

public class PenDownQuery extends MultipleCommand {
	private static final double TRUE = 1.0;
	private static final double FALSE = 0.0;
	
	public PenDownQuery(List<Executable> argv)
			throws SyntacticErrorException {
		super(argv);
	}

	@Override
	public double execute(TurtleLog log)
			throws SyntacticErrorException {
		ActorState prev = log.peekLast();
		if (prev.getPen().isDown()) {return TRUE;}
		else {return FALSE;}
	}

	@Override
	public String getName() {
		return "pendown?";
	}
}