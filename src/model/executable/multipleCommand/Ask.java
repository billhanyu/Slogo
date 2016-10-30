package model.executable.multipleCommand;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import exception.SyntacticErrorException;
import model.Executable;
import model.LogHolder;

public class Ask extends TellAskProto {

	public Ask(List<Executable> argv) throws SyntacticErrorException {
		super(argv);
	}

	@Override
	public double execute(LogHolder log) throws SyntacticErrorException {
		Collection<Integer> copy = new ArrayList<>(log.getActiveIDs());
		double result = 0;
		List<Integer> indices = this.getActiveIDs(log);
		log.setActiveIDs(indices);
		result = this.getArgs().get(1).execute(log);
		log.setActiveIDs(copy);
		return result;
	}

	@Override
	public String getName() {
		return "ask";
	}

}
