package model.executable.multipleCommand;

import java.util.List;

import exception.SyntacticErrorException;
import model.Executable;
import model.LogHolder;
import model.executable.SingleCommand;

public class Id extends SingleCommand {

	public Id(List<Executable> argv) throws SyntacticErrorException {
		super(argv);
	}

	@Override
	public double execute(LogHolder log) throws SyntacticErrorException {
		double result = 0;
		for (int id : log.getActiveIDs()) {
			result = id;
		}
		return result;
	}

	@Override
	public String getName() {
		return "id";
	}

}
