package model.executable;

import java.util.List;
import java.util.Stack;

import exception.SyntacticErrorException;
import model.TurtleLog;
import model.Executable;
import model.StackFrame;

public class ProcedureImpl{
	
	private String name;
	// TODO (cx15): when called, push a new stackFrame onto stack, populate it using variables from previous stackframe, subsequent command access value from the new stackfrome 
	private Stack<StackFrame> stack;
	private CodeBlock procedure;
	
	public ProcedureImpl(String name,
						 List<Executable> paramNames,
						 CodeBlock procedure) throws SyntacticErrorException {
		this.stack = new Stack<>();
		this.name = name;
		this.procedure = procedure;
	}

	public double execute(TurtleLog log, StackFrame stackFrame, List<Executable> argv) 
			throws SyntacticErrorException {
		// param assumed to be in stackFrame
		// TODO (cx15): open up another stack frame
		double ret = procedure.execute(log);
		// TODO (cx15): collapse the stack frame
		return ret;
	}

	public String getName() {
		return this.name;
	}

	private void validateArgv() throws SyntacticErrorException {
		if (!(this.getArgs().get(0) instanceof CodeBlock)
				|| !(this.getArgs().get(1) instanceof CodeBlock)) {
			throw new SyntacticErrorException();
		}		
	}
}
