package model.executable.stdCommand.controlFlow;

import java.util.ArrayList;
import java.util.List;

import exception.SyntacticErrorException;
import model.Executable;
import model.TurtleLog;
import model.executable.CodeBlock;
import model.executable.Constant;
import model.executable.StandardCommand;
import model.executable.Variable;

public class For extends StandardCommand{
	//TODO: make a variable assigned to each succeeding loop value
	//so that it can be accessed by commands
	private Variable var;
	private double start;
	private double end;
	private double increment;
	private CodeBlock body;
	private List<Executable> varStartEndInc;
	
	public For(List<Executable> argv)
			throws SyntacticErrorException {
		super(argv);
	}

	@Override
	public double execute(TurtleLog log)
			throws SyntacticErrorException {
		
		this.start = varStartEndInc.get(1).execute(log);
		this.end = varStartEndInc.get(2).execute(log);
		this.increment = varStartEndInc.get(3).execute(log);
		
		double ret = 0;
		List<Executable> makeRepCt = new ArrayList<Executable>();
		Constant ct = new Constant("RepCount", start);
		for (double i = start; i <= end; i += increment){
			ct.setValue(i);
			makeRepCt.add(ct);
			makeRepCt.add(var);
			Make myMake = new Make(makeRepCt);
			myMake.execute(log);
			makeRepCt.clear();
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
		this.varStartEndInc = varStartEndIncBlock.unravel();
		
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
		this.body = (CodeBlock) this.getArgs().get(1);
	}

}
