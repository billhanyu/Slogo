package model.executable.multipleCommand.display;

import java.util.List;

import exception.SyntacticErrorException;
import javafx.scene.paint.Color;
import model.ActorState;
import model.Executable;
import model.TurtleLog;
import model.TurtleState;
import model.executable.MultipleCommand;
import model.executable.StandardCommand;

public class Shape extends MultipleCommand{
	
	public Shape(List<Executable> argv)
			throws SyntacticErrorException {
		super(argv);
	}

	@Override
	public double execute(TurtleLog log)
			throws SyntacticErrorException {
		ActorState prev = log.peekLast();
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