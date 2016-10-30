package model.executable.multipleCommand.display;

import java.util.List;

import exception.SyntacticErrorException;
import model.ActorState;
import model.Executable;
import model.TurtleLog;
import model.TurtleState;
import model.executable.MultipleCommand;
import model.executable.StandardCommand;

public class SetPenColor extends MultipleCommand{
	
	public SetPenColor(List<Executable> argv)
			throws SyntacticErrorException {
		super(argv);
	}

	@Override
	public double execute(TurtleLog log)
			throws SyntacticErrorException {
		ActorState prev = log.peekLast();
		double index = this.getArgs().get(0).execute(log);
		delta = new TurtleState();
		prev.duplicateOnto(delta);
		//TODO: get to data structure containing index to color mappings
		//get color corresponding to index
		//delta.getPen().setColor(color);
		log.append(delta);
		return index;
	}

	@Override
	public String getName() {
		return "setpencolor";
	}
}