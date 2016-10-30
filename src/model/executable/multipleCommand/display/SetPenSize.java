package model.executable.multipleCommand.display;

import java.util.List;

import exception.SyntacticErrorException;
import model.Executable;
import model.TurtleLog;
import model.executable.MultipleCommand;

public class SetPenSize extends MultipleCommand {
	
	public SetPenSize(List<Executable> argv)
			throws SyntacticErrorException {
		super(argv);
	}

	@Override
	public double execute(TurtleLog log)
			throws SyntacticErrorException {
		double thickness = this.getArgs().get(0).execute(this.getLogHolder());
		log.peekLast().getPen().setThickness(thickness);
		return thickness;
	}

	@Override
	public String getName() {
		return "setpensize";
	}
}