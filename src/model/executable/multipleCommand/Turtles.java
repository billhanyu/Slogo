package model.executable.multipleCommand;

import java.util.List;

import exception.SyntacticErrorException;
import model.Executable;
import model.LogHolder;
import model.executable.StaticCommand;

public class Turtles extends StaticCommand {

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
