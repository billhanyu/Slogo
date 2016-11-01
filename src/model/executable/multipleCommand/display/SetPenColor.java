package model.executable.multipleCommand.display;

import java.util.List;

import exception.SyntacticErrorException;
import model.Executable;
import model.TurtleLog;
import model.TurtleState;
import model.executable.TurtleCommand;

public class SetPenColor extends TurtleCommand {
	
	public SetPenColor(List<Executable> argv)
			throws SyntacticErrorException {
		super(argv);
	}

	@Override
	public double execute(TurtleLog log)
			throws SyntacticErrorException {
		double index = this.getArgs().get(0).execute(this.getLogHolder());
		delta = new TurtleState();
		log.peekLast().duplicateOnto(delta);
		delta.getPen().setColor(
				this.getLogHolder().getPalette().getColor((int)index));
		log.append(delta);
		return index;
	}

	@Override
	public String getName() {
		return "setpencolor";
	}
	
}