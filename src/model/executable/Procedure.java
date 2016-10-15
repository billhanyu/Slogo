package model.executable;

import java.util.List;
import java.util.Stack;

import model.TurtleLog;
import model.TurtleState;
import model.StackFrame;

public class Procedure extends Command{
	
	private String name;
	private Stack<StackFrame> stack;
	// TODO (cx15): when called, push a new stackFrame onto stack, populate it using variables from previous stackframe, subsequent command access value from the new stackfrome 
	private List<Command> procedure;
	private TurtleLog localLog;
	
	public Procedure(String name, Stack<StackFrame> stack, List<Command> procedure) {
		this.name = name;
		this.stack = stack;
		this.procedure = procedure;
		this.localLog = new TurtleLog();
	}

	@Override
	public double execute() {
		// TODO (cx15): open up stack frame
		for (Command cmd : procedure) {
			cmd.execute();
			cmd.appendToLog(localLog);
		}
		// TODO (cx15): collapse stack frame
		return 0;
	}

	@Override
	public void appendToLog(TurtleLog log) {
		for (TurtleState entry : localLog) {
			log.append(entry);
		}
	}

	@Override
	public String getName() {
		return this.name;
	}
}
