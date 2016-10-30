package model.executable;

import java.util.List;

import exception.SyntacticErrorException;
import model.Executable;

/**
 * @author billyu
 * command type that operates a level above turtles
 */
public abstract class SingleCommand extends StandardCommand {

	public SingleCommand(List<Executable> argv) throws SyntacticErrorException {
		super(argv);
	}
	
}
