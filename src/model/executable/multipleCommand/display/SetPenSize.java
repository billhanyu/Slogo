package model.executable.multipleCommand.display;

import java.util.List;

import exception.SyntacticErrorException;
import model.ActorState;
import model.Executable;
import model.Pen;
import model.TurtleLog;
import model.TurtleState;
import model.executable.MultipleCommand;
import model.executable.StandardCommand;

public class SetPenSize extends MultipleCommand{
	
	public SetPenSize(List<Executable> argv)
			throws SyntacticErrorException {
		super(argv);
	}

	@Override
	public double execute(TurtleLog log)
			throws SyntacticErrorException {
		ActorState prev = log.peekLast();
		double thickness = this.getArgs().get(0).execute(log);
		delta = new TurtleState();
		prev.duplicateOnto(delta);
		delta.getPen().setThickness(thickness);
		log.append(delta);
		return thickness;
	}

	@Override
	public String getName() {
		return "setpensize";
	}
}