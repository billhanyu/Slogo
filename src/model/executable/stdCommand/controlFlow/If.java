package model.executable.stdCommand.controlFlow;

import java.util.List;

import exception.SyntacticErrorException;
import model.Executable;
import model.TurtleLog;
import model.executable.CodeBlock;
import model.executable.StandardCommand;
import model.executable.Variable;

public class If extends StandardCommand {
	
	private Executable expr;
	private CodeBlock body;

	public If(List<Executable> argv)
			throws SyntacticErrorException {
		super(argv);
	}

	@Override
	public double execute(TurtleLog log)
			throws SyntacticErrorException {
		if (expr.execute(log) != 0) {
			return body.execute(log);
		} else {
			return 0;
		}
	}

	@Override
	public String getName() {
		return "if";
	}
	
	@Override
	protected void validateArgv() throws SyntacticErrorException {
		if (!(this.getArgs().get(0) instanceof Executable)
				|| !(this.getArgs().get(1) instanceof CodeBlock)) {
			throw new SyntacticErrorException();
		}
		this.expr = this.getArgs().get(0);
		this.body = (CodeBlock) this.getArgs().get(1);
		for (Variable var : body.getLocalVarRefs().getImmutableValues()) {
			if (var.getName().equals(expr.getName())) {
				var.setExpression(expr);
			}
		}
	}
}
