package model.executable;

import java.util.List;
import java.util.Stack;

import model.TurtleLog;
import model.ActorState;
import model.Executable;
import model.StackFrame;

public class Procedure extends Command{
	
	private String name;
	// TODO (cx15): when called, push a new stackFrame onto stack, populate it using variables from previous stackframe, subsequent command access value from the new stackfrome 
	private Stack<StackFrame> stack;
	private List<Command> procedure;
	private TurtleLog localLog;
	
	public Procedure(String name, List<Executable> argv, Stack<StackFrame> stack, List<Command> procedure) {
		super(argv); // used the names only, to find value from stack
		this.stack = stack;
		this.name = name;
		this.procedure = procedure;
		this.localLog = new TurtleLog();
	}

	@Override
	public double execute() {
		// TODO (cx15): open up another stack frame
		for (Command cmd : procedure) {
			cmd.execute();
			cmd.appendToLog(localLog);
		}
		// TODO (cx15): collapse the stack frame
		return 0;
	}

	@Override
	public void appendToLog(TurtleLog log) {
		for (ActorState entry : localLog) {
			log.append(entry);
		}
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public int getExpectedNumArgs() {
		// TODO Auto-generated method stub
		return 0;
	}
}
