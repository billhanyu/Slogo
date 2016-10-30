package model.executable.multipleCommand.display;

import java.util.List;

import exception.SyntacticErrorException;
import model.Executable;
import model.TurtleLog;
import model.executable.MultipleCommand;

public class SetPenColor extends MultipleCommand {
	
	public SetPenColor(List<Executable> argv)
			throws SyntacticErrorException {
		super(argv);
	}

	@Override
	public double execute(TurtleLog log)
			throws SyntacticErrorException {
		double index = this.getArgs().get(0).execute(this.getLogHolder());
		log.peekLast().getPen().setColor(
				this.getLogHolder().getPalette().getColor((int)index));
		return index;
	}

	@Override
	public String getName() {
		return "setpencolor";
	}
	
}