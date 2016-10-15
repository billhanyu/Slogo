package model.executable;

import java.util.List;

import model.Executable;
import model.TurtleLog;
import model.TurtleState;

public class Procedure extends Command{
	
	private String name;
	private List<Executable> argv;
	private List<Command> procedure;
	private TurtleLog localLog;
	
	public Procedure(String name, List<Executable> argv, List<Command> procedure) {
		this.name = name;
		this.argv = argv;
		this.procedure = procedure;
		this.localLog = new TurtleLog();
	}

	@Override
	public double execute() {
		for (Command cmd : procedure) {
			cmd.execute();
			cmd.appendToLog(localLog);
		}
		return 0;
	}

	@Override
	public void appendToLog(TurtleLog log) {
		for (TurtleState entry : localLog) {
			log.append(entry);
		}
	}
}
