package model.executable.singleCommand.bool;

import java.util.List;

import exception.SyntacticErrorException;
import model.Executable;
import model.LogHolder;
import model.executable.StaticCommand;
import util.Utils;

public class NotEqual extends StaticCommand {

	public NotEqual(List<Executable> argv) throws SyntacticErrorException {
		super(argv);
	}

	@Override
	public double execute(LogHolder log) throws SyntacticErrorException {
		if (Utils.doubleEqual(this.getArgs().get(0).execute(log), 
				this.getArgs().get(1).execute(log))) {
			return 0;
		}
		return 1;
	}

	@Override
	public String getName() {
		return "notequal";
	}

}
