package model.executable;

import java.util.List;

import exception.SyntacticErrorException;
import model.Executable;
import model.TurtleLog;

public class ProcedureStub extends Command {
	
	private String name;
	private ProcedureImpl impl;

	public ProcedureStub(String name,
						 List<Executable> argv,
						 ProcedureImpl impl)
			throws SyntacticErrorException {
		super(argv);
		this.name = name;
		this.impl = impl;
	}

	@Override
	public double execute(TurtleLog log) throws SyntacticErrorException {
		return impl.execute(log, getArgs());
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	protected void validateArgv() throws SyntacticErrorException {
		
	}
}
