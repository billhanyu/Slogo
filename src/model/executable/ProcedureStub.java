package model.executable;

import java.util.List;

import exception.SyntacticErrorException;
import model.Executable;
import model.SemanticsRegistry;
import model.Token;
import model.TurtleLog;

public class ProcedureStub extends Command {
	
	private String name;
	private SemanticsRegistry semanticsRegistry;

	public ProcedureStub(Token token,
						 List<Executable> argv,
						 SemanticsRegistry semanticsRegistry)
			throws SyntacticErrorException {
		super(argv);
		this.name = token.toString();
		this.semanticsRegistry = semanticsRegistry;
	}

	@Override
	public double execute(TurtleLog log) throws SyntacticErrorException {
		return semanticsRegistry.getImpl(name).execute(log, getArgs());
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	protected void validateArgv() throws SyntacticErrorException {
		
	}
}
