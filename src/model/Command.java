package model;

import java.util.ArrayList;
import java.util.List;

public class Command implements Executable {
	
	private List<Command> subCommands;
	private TurtleLog log;
	
	public Command() {
		subCommands = new ArrayList<Command>();
	}

	@Override
	public void execute() {
		// TODO Does its stuff
		
		if (subCommands.size() == 0) {
			appendState(null);
			//TODO initialize the TurtleState and append
		}
	}

	private void appendState(TurtleState state) {
		log.append(state);
	}
	
}
