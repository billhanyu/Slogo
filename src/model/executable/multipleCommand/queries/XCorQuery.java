package model.executable.multipleCommand.queries;

import java.util.List;

import exception.SyntacticErrorException;
import model.ActorState;
import model.Executable;
import model.TurtleLog;
import model.executable.TurtleCommand;

public class XCorQuery extends TurtleCommand {
	
	public XCorQuery(List<Executable> argv)
			throws SyntacticErrorException {
		super(argv);
	}

	@Override
	public double execute(TurtleLog log)
			throws SyntacticErrorException {
		ActorState prev = log.peekLast();
		return prev.getPositionX();
	}

	@Override
	public String getName() {
		return "xcor";
	}
}