package model.executable.singleCommand.controlFlow;

import java.util.List;

import exception.SyntacticErrorException;
import model.Executable;
import model.LogHolder;
import model.executable.CodeBlock;
import model.executable.Constant;
import model.executable.StaticCommand;
import model.executable.Variable;

public class For extends StaticCommand{
	//TODO: make a variable assigned to each succeeding loop value
	//so that it can be accessed by commands
	private Variable var;
	private int start;
	private int end;
	private int increment;
	private CodeBlock body;
	
	public For(List<Executable> argv)
			throws SyntacticErrorException {
		super(argv);
	}

	@Override
	public double execute(LogHolder log)
			throws SyntacticErrorException {
		CodeBlock varLimBlock = (CodeBlock) this.getArgs().get(0);
		var = (Variable) varLimBlock.getLocalVarRefs().getImmutableValues().get(0);
		increment = (int) varLimBlock.getPendingArgs().get(0).execute(log);
		end = (int) varLimBlock.getPendingArgs().get(1).execute(log);
		start = (int) varLimBlock.getPendingArgs().get(2).execute(log);
		body = (CodeBlock) this.getArgs().get(1);
		body.getLocalVarRefs().get(var.getName()).setExpression(var);
		
		double ret = 0;
		for (int i = start; i <= end; i+=increment) {
			var.setExpression(new Constant(null, i));
			ret = body.execute(log);
		}
		return ret;
	}

	@Override
	public String getName() {
		return "for";
	}
	
	@Override
	protected void validateArgv() throws SyntacticErrorException {
		if (!(this.getArgs().get(0) instanceof CodeBlock)
				|| !(this.getArgs().get(1) instanceof CodeBlock)) {
			throw new SyntacticErrorException();
		}
	}

}
