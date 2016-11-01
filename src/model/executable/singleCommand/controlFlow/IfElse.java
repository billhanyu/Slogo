package model.executable.singleCommand.controlFlow;

import java.util.List;

import exception.SyntacticErrorException;
import model.Executable;
import model.LogHolder;
import model.executable.CodeBlock;
import model.executable.StaticCommand;

public class IfElse extends StaticCommand {
	
	private Executable expr;
	private CodeBlock trueBody;
	private CodeBlock falseBody;

	public IfElse(List<Executable> argv)
			throws SyntacticErrorException {
		super(argv);
	}

	@Override
	public double execute(LogHolder log)
			throws SyntacticErrorException {
		if (expr.execute(log) != 0) {
			return trueBody.execute(log);
		} else {
			return falseBody.execute(log);
		}
	}

	@Override
	public String getName() {
		return "ifelse";
	}
	
	@Override
	protected void validateArgv() throws SyntacticErrorException {
		if (!(this.getArgs().get(0) instanceof Executable)
				|| !(this.getArgs().get(1) instanceof CodeBlock)
				|| !(this.getArgs().get(2) instanceof CodeBlock)) {
			throw new SyntacticErrorException();
		}
		this.expr = this.getArgs().get(0);
		this.trueBody = (CodeBlock) this.getArgs().get(1);
		this.falseBody = (CodeBlock) this.getArgs().get(2);
	}
}
