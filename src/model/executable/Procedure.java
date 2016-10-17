package model.executable;

import java.util.List;
import java.util.Stack;

import exception.SyntacticErrorException;
import model.TurtleLog;
import model.Executable;
import model.StackFrame;

public class Procedure extends Command{
	
	private String name;
	// TODO (cx15): when called, push a new stackFrame onto stack, populate it using variables from previous stackframe, subsequent command access value from the new stackfrome 
	private Stack<StackFrame> stack;
	private CodeBlock procedure;
	
	public Procedure(String name,
					 List<Executable> argv,
					 Stack<StackFrame> stack,
					 CodeBlock procedure) {
		super(argv); // used the names only, to find value from stack
		this.stack = stack;
		this.name = name;
		this.procedure = procedure;
	}

	@Override
	public double execute(TurtleLog log) 
			throws SyntacticErrorException {
		// TODO (cx15): open up another stack frame
		double ret = procedure.execute(log);
		// TODO (cx15): collapse the stack frame
		return ret;
	}

	@Override
	public String getName() {
		return this.name;
	}
}
