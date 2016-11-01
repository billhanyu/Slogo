package model.executable.multipleCommand.display;

import java.util.List;

import exception.SyntacticErrorException;
import model.ActorState;
import model.Executable;
import model.TurtleLog;
import model.executable.TurtleCommand;

public class Shape extends TurtleCommand{
	
	public Shape(List<Executable> argv)
			throws SyntacticErrorException {
		super(argv);
	}

	@Override
	public double execute(TurtleLog log)
			throws SyntacticErrorException {
		//Shape shape = prev.getShape();
		//TODO: get to data structure containing index to shape mappings
		//get index corresponding to shape
		//return index;
		return 0;
	}

	@Override
	public String getName() {
		return "shape";
	}
}