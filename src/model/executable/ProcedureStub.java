package model.executable;

import java.util.List;
import java.util.Stack;

import exception.SyntacticErrorException;
import model.Executable;
import model.SemanticsRegistry;
import model.StackFrame;
import model.TurtleLog;

public class ProcedureStub extends Command {
	
	private String name;
	private SemanticsRegistry semanticsRegistry;
	private StackFrame globalVars;

	public ProcedureStub(String name,
						 List<Executable> argv,
						 StackFrame globalVars,
						 SemanticsRegistry semanticsRegistry)
			throws SyntacticErrorException {
		super(argv);
		this.name = name;
		this.globalVars = globalVars;
		this.semanticsRegistry = semanticsRegistry;
	}

	@Override
	public double execute(TurtleLog log) throws SyntacticErrorException {
		ProcedureImpl impl = semanticsRegistry.getImpl(name);
		// TODO (cx15): put param in globalVars
		double ret = impl.execute(log, globalVars);
		// TODO (cx15): remove param from globalVars
		return ret;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void validateArgv() throws SyntacticErrorException {
		// TODO Auto-generated method stub
		
	}

}
