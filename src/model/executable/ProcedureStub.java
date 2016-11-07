package model.executable;

import java.util.List;

import exception.SyntacticErrorException;
import model.Executable;
import model.LogHolder;
import model.SemanticsRegistry;

/**
 * Dynamic binding at runtime through semantics registry
 * @author CharlesXu
 */
public class ProcedureStub extends Command {
	
	private SemanticsRegistry semanticsRegistry;

	public ProcedureStub(List<Executable> argv)
			throws SyntacticErrorException {
		super(argv);
	}

	@Override
	public double execute(LogHolder log) throws SyntacticErrorException {
		return semanticsRegistry.getImpl(name).execute(log, getArgs());
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	protected void validateArgv() throws SyntacticErrorException {
		
	}
	
	public void setSemantics(SemanticsRegistry semanticsRegistry) {
		this.semanticsRegistry = semanticsRegistry;
	}
}
