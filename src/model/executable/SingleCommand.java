package model.executable;

import java.util.List;

import exception.SyntacticErrorException;
import model.Executable;

public abstract class SingleCommand extends StandardCommand {

	public SingleCommand(List<Executable> argv) throws SyntacticErrorException {
		super(argv);
	}
	
}
