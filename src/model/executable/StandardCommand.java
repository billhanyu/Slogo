package model.executable;

import java.util.List;

import exception.SyntacticErrorException;
import model.ActorState;
import model.Executable;
import model.TurtleLog;
import model.TurtleState;

/**
 * A Command is assumed to be executed in a given context by stackFrame
 * that contains its argv. It will never result in another stackFrame. If one
 * feels such need, use Procedure
 */
public abstract class StandardCommand extends Command {
	
	protected ActorState delta;
	
	public StandardCommand(List<Executable> argv)
			throws SyntacticErrorException {
		super(argv);
		delta = new TurtleState();
	}

	@Override
	public abstract double execute(TurtleLog log)
			throws SyntacticErrorException;
	
	@Override
	public abstract String getName();
	
	@Override
	protected void validateArgv()
			throws SyntacticErrorException {
		// does nothing, by default takes any Executable as argument
	}
}
