package model.executable.stdCommand.bool;

import java.util.List;

import exception.SyntacticErrorException;
import model.Executable;
import model.TurtleLog;
import model.executable.StandardCommand;
import util.Utils;

public class Equal extends StandardCommand {

	public Equal(List<Executable> argv) throws SyntacticErrorException {
		super(argv);
	}

	@Override
	public double execute(TurtleLog log) throws SyntacticErrorException {
		if (Utils.doubleEqual(this.getArgs().get(0).execute(log), 
				this.getArgs().get(1).execute(log))) {
			return 1;
		}
		return 0;
	}

	@Override
	public String getName() {
		return "equal";
	}

}
