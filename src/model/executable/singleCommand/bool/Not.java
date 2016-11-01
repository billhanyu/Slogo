package model.executable.singleCommand.bool;

import java.util.List;

import exception.SyntacticErrorException;
import model.Executable;
import model.LogHolder;
import model.executable.StaticCommand;
import util.Utils;

public class Not extends StaticCommand {

	public Not(List<Executable> argv) throws SyntacticErrorException {
		super(argv);
	}

	@Override
	public double execute(LogHolder log) throws SyntacticErrorException {
		if (Utils.doubleEqual(this.getArgs().get(0).execute(log), 0)) {
			return 1;
		}
		return 0;
	}

	@Override
	public String getName() {
		return "not";
	}

}
