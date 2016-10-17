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
public abstract class StandardCommand extends Command{
	
	// TODO(cx15): define a final static name for each subclass
	protected ActorState delta;
	
	public StandardCommand(List<Executable> argv) {
		super(argv);
		delta = new TurtleState();
	}

	@Override
	public abstract double execute(TurtleLog log)
			throws SyntacticErrorException;
	
	@Override
	public abstract String getName();
}
