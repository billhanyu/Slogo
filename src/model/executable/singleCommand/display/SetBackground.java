package model.executable.singleCommand.display;

import java.util.List;

import exception.SyntacticErrorException;
import model.Executable;
import model.LogHolder;
import model.executable.SingleCommand;

public class SetBackground extends SingleCommand{
	
	public SetBackground(List<Executable> argv)
			throws SyntacticErrorException {
		super(argv);
	}

	@Override
	public double execute(LogHolder log)
			throws SyntacticErrorException {
		double index = this.getArgs().get(0).execute(log);
		log.getWorkspaceState().setBackgroundColor(log.getPalette().getColor((int) index));
		return index;
	}

	@Override
	public String getName() {
		return "setbackground";
	}
}