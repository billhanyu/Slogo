package model.executable.multipleCommand;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import exception.SyntacticErrorException;
import model.Executable;
import model.LogHolder;
import model.executable.StaticCommand;

/**
 * @author billyu
 * ask [ condition ] [ command ]
 * loops over all turtles to test condition
 */
public class AskWith extends StaticCommand {

	public AskWith(List<Executable> argv) throws SyntacticErrorException {
		super(argv);
	}

	@Override
	public double execute(LogHolder log) throws SyntacticErrorException {
		// back up the original active IDs
		Collection<Integer> copy = new ArrayList<>(log.getActiveIDs());
		double result = 0;
		List<Integer> active = new ArrayList<>();
		// test if each turtle satisfies the condition
		for (int candidate : log.getAllIDs()) {
			active.clear();
			active.add(candidate);
			log.setActiveIDs(active);
			if (this.getArgs().get(0).execute(log) != 0) {
				// satisfies the condition, execute the commands
				result = this.getArgs().get(1).execute(log);
			}
		}
		// set back the original active IDs
		log.setActiveIDs(copy);
		return result;
	}

	@Override
	public String getName() {
		return "askwith";
	}

}
