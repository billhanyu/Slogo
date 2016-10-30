package model.executable.singleCommand.controlFlow;

import java.util.List;

import exception.SyntacticErrorException;
import model.Executable;
import model.LogHolder;
import model.executable.CodeBlock;
import model.executable.Constant;
import model.executable.SingleCommand;
import model.executable.Variable;

public class DoTimes extends SingleCommand{
	
	private Variable var;
	private Executable limit;
	private CodeBlock body;
	
	public DoTimes(List<Executable> argv)
			throws SyntacticErrorException {
		super(argv);
	}

	@Override
	public double execute(LogHolder log)
			throws SyntacticErrorException {
		int times = (int) limit.execute(log);
		double ret = 0;
		for (int i = 1; i <= times; i++) {
			var.setExpression(new Constant(null, i));
			ret = body.execute(log);
		}
		return ret;
	}

	@Override
	public String getName() {
		return "dotimes";
	}
	
	@Override
	protected void validateArgv() throws SyntacticErrorException {
		if (!(this.getArgs().get(0) instanceof CodeBlock)
				|| !(this.getArgs().get(1) instanceof CodeBlock)) {
			throw new SyntacticErrorException();
		}
		CodeBlock varLimBlock = (CodeBlock) this.getArgs().get(0);
		this.var = (Variable) varLimBlock.getLocalVarRefs().getImmutableValues().get(0);
		this.limit = varLimBlock.getPendingArgs().get(0);
		this.body = (CodeBlock) this.getArgs().get(1);
		this.body.getLocalVarRefs().get(var.getName()).setExpression(var);
	}

}
