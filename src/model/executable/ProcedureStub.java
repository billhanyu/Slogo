package model.executable;

import java.util.List;
import java.util.Stack;

import exception.SyntacticErrorException;
import model.Executable;
import model.StackFrame;
import model.TurtleLog;

public class ProcedureStub extends Command {
	
	private String name;
	private ProcedureImpl impl;
	private StackFrame globalVars;

	public ProcedureStub(String name,
						 List<Executable> argv,
						 StackFrame globalVars,
						 ProcedureImpl impl)
			throws SyntacticErrorException {
		super(argv);
		this.name = name;
		this.globalVars = globalVars;
		this.impl = impl;
	}

	@Override
	public double execute(TurtleLog log) throws SyntacticErrorException {
		// TODO (cx15): put param in globalVars
		double ret = impl.execute(log, globalVars, argv);
		// TODO (cx15): remove param from globalVars
		return ret;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	protected void validateArgv() throws SyntacticErrorException {
		
	}

}
