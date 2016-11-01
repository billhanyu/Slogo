package model.executable.multipleCommand.display;

import java.util.List;

import exception.SyntacticErrorException;
import model.Executable;
import model.TurtleLog;
import model.executable.TurtleCommand;

public class SetShape extends TurtleCommand{
	
	public SetShape(List<Executable> argv)
			throws SyntacticErrorException {
		super(argv);
	}

	@Override
	public double execute(TurtleLog log)
			throws SyntacticErrorException {
//		ActorState prev = log.peekLast();		
//		double index = this.getArgs().get(0).execute(log);
//		delta = new TurtleState();
//		prev.duplicateOnto(delta);
//		//TODO: get to data structure containing index to shape mappings
//		//get shape corresponding to index value
//		//delta.setShape(shape);
//		log.append(delta);
//		return index;
		return 0;
	}

	@Override
	public String getName() {
		return "setshape";
	}
}