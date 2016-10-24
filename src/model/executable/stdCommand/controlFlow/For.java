package model.executable.stdCommand.controlFlow;

import java.util.List;

import exception.SyntacticErrorException;
import model.Executable;
import model.TurtleLog;
import model.executable.CodeBlock;
import model.executable.Constant;
import model.executable.StandardCommand;
import model.executable.Variable;

public class For extends StandardCommand{
	
	private Variable var;
	private double start;
	private double end;
	private double increment;
	private CodeBlock body;
	
	public For(List<Executable> argv)
			throws SyntacticErrorException {
		super(argv);
	}

	@Override
	public double execute(TurtleLog log)
			throws SyntacticErrorException {
		
		double ret = 0;
		for (double i = start; i <= end; i += increment){
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
		CodeBlock varStartEndIncBlock = (CodeBlock) this.getArgs().get(0);
		List<Executable> varStartEndInc = varStartEndIncBlock.unravel();
		
		try {
			if (!(varStartEndInc.get(0) instanceof Variable)
					|| !(varStartEndInc.get(1) instanceof Constant)
					|| !(varStartEndInc.get(2) instanceof Constant)
					|| !(varStartEndInc.get(3) instanceof Constant)) {
				throw new SyntacticErrorException();
			}
		} catch (IndexOutOfBoundsException e) {
			throw new SyntacticErrorException();
		}
		
		this.var = (Variable) varStartEndInc.get(0);
		this.start = Double.parseDouble(varStartEndInc.get(1).getName());
		this.end = Double.parseDouble(varStartEndInc.get(2).getName());
		this.increment = Double.parseDouble(varStartEndInc.get(3).getName());	
		this.body = (CodeBlock) this.getArgs().get(1);
	}

}
