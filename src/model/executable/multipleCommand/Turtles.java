package model.executable.multipleCommand;

import java.util.List;

import exception.SyntacticErrorException;
import model.Executable;
import model.LogHolder;
import model.executable.SingleCommand;

public class Turtles extends SingleCommand {

	public Turtles(List<Executable> argv) throws SyntacticErrorException {
		super(argv);
	}

	@Override
	public double execute(LogHolder log) throws SyntacticErrorException {
		return log.getAllIDs().size();
	}

	@Override
	public String getName() {
		return "turtles";
	}

}
