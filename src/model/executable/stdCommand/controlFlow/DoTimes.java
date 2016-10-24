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

public class DoTimes extends StandardCommand{
	//TODO: make a variable assigned to each succeeding loop value
	//so that it can be accessed by commands
	private Variable var;
	private double end;
	private CodeBlock body;
	private List<Executable> varLim;
	
	public DoTimes(List<Executable> argv)
			throws SyntacticErrorException {
		super(argv);
	}

	@Override
	public double execute(TurtleLog log)
			throws SyntacticErrorException {

		this.end = varLim.get(1).execute(log);
		
		double ret = 0;
		List<Executable> makeRepCt = new ArrayList<Executable>();
		Constant ct = new Constant("RepCount", 1);
		for (double i = 1; i <= end; i++){
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
		return "dotimes";
	}
	
	@Override
	protected void validateArgv() throws SyntacticErrorException {
		
		if (!(this.getArgs().get(0) instanceof CodeBlock)
				|| !(this.getArgs().get(1) instanceof CodeBlock)) {
			throw new SyntacticErrorException();
		}
		CodeBlock varLimBlock = (CodeBlock) this.getArgs().get(0);
		this.varLim = varLimBlock.unravel();
		
		try {
			if (!(varLim.get(0) instanceof Variable)
					|| !(varLim.get(1) instanceof Constant)) {
				throw new SyntacticErrorException();
			}
		} catch (IndexOutOfBoundsException e) {
			throw new SyntacticErrorException();
		}
		
		this.var = (Variable) varLim.get(0);
		this.body = (CodeBlock) this.getArgs().get(1);
	}

}
