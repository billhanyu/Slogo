package model.executable.singleCommand.bool;

import java.util.List;

import exception.SyntacticErrorException;
import model.Executable;
import model.LogHolder;
import model.executable.SingleCommand;

public class Greater extends SingleCommand {

	public Greater(List<Executable> argv) throws SyntacticErrorException {
		super(argv);
	}

	@Override
	public double execute(LogHolder log) throws SyntacticErrorException {
		if (this.getArgs().get(0).execute(log) > this.getArgs().get(1).execute(log)) {
			return 1;
		}
		return 0;
	}

	@Override
	public String getName() {
		return "greater";
	}

}
