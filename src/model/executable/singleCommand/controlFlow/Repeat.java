package model.executable.singleCommand.controlFlow;

import java.util.List;

import exception.SyntacticErrorException;
import model.Executable;
import model.LogHolder;
import model.executable.CodeBlock;
import model.executable.Constant;
import model.executable.StaticCommand;
import model.executable.Variable;

public class Repeat extends StaticCommand {
	
	public static final String REPCOUNT = ":repcount";
	
	private Executable expr;
	private CodeBlock body;

	public Repeat(List<Executable> argv)
			throws SyntacticErrorException {
		super(argv);
	}

	@Override
	public double execute(LogHolder log)
			throws SyntacticErrorException {
		int times = (int) expr.execute(log);
		Variable repcount = body.getLocalVarRefs().get(REPCOUNT);
		double ret = 0;
		for (int i = 1; i <= times; i++) {
			if (repcount != null)
				repcount.setExpression(new Constant(null, i));
			ret = body.execute(log);
		}
		return ret;
	}

	@Override
	public String getName() {
		return "repeat";
	}
	
	@Override
	protected void validateArgv() throws SyntacticErrorException {
		if (!(this.getArgs().get(0) instanceof Executable)
				|| !(this.getArgs().get(1) instanceof CodeBlock)) {
			throw new SyntacticErrorException();
		}
		this.expr = this.getArgs().get(0);
		this.body = (CodeBlock) this.getArgs().get(1);
	}
}
