package model.executable.multipleCommand;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import exception.SyntacticErrorException;
import model.Executable;
import model.LogHolder;
import model.executable.SingleCommand;

public class AskWith extends SingleCommand {

	public AskWith(List<Executable> argv) throws SyntacticErrorException {
		super(argv);
	}

	@Override
	public double execute(LogHolder log) throws SyntacticErrorException {
		Collection<Integer> copy = new ArrayList<>(log.getActiveIDs());
		double result = 0;
		Collection<Integer> ids = log.getAllIDs();
		Collection<Integer> actives = new ArrayList<>();
		for (int id : ids) {
			if (this.getArgs().get(0).execute(log) != 0) {
				// TODO: bug: currently execute on log holder loops over every active turtlelog
				// instead of querying one one turtlelog
				actives.add(id);
			}
		}
		System.out.println(actives);
		log.setActiveIDs(actives);
		result = this.getArgs().get(1).execute(log);
		log.setActiveIDs(copy);
		return result;
	}

	@Override
	public String getName() {
		return "askwith";
	}

}
