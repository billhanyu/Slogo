package model.executable.multipleCommand.queries;

import java.util.List;

import exception.SyntacticErrorException;
import model.ActorState;
import model.Executable;
import model.TurtleLog;
import model.executable.MultipleCommand;

public class YCorQuery extends MultipleCommand {
	
	public YCorQuery(List<Executable> argv)
			throws SyntacticErrorException {
		super(argv);
	}

	@Override
	public double execute(TurtleLog log)
			throws SyntacticErrorException {
		ActorState prev = log.peekLast();
		return prev.getPositionY();
	}

	@Override
	public String getName() {
		return "ycor";
	}
}