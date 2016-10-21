package model.executable.stdCommand.bool;

import java.util.List;

import exception.SyntacticErrorException;
import model.Executable;
import model.TurtleLog;
import model.executable.StandardCommand;

public class Less extends StandardCommand {

	public Less(List<Executable> argv) throws SyntacticErrorException {
		super(argv);
	}

	@Override
	public double execute(TurtleLog log) throws SyntacticErrorException {
		if (this.getArgs().get(0).execute(log) < this.getArgs().get(1).execute(log)) {
			return 1;
		}
		return 0;
	}

	@Override
	public String getName() {
		return "less";
	}

}
