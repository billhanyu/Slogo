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

public class Repeat extends StandardCommand {
	//TODO: make a variable ":repcount" assigned to each succeeding loop value
	//so that it can be accessed by commands
	
	private Executable expr;
	private CodeBlock body;

	public Repeat(List<Executable> argv)
			throws SyntacticErrorException {
		super(argv);
	}

	@Override
	public double execute(TurtleLog log)
			throws SyntacticErrorException {
		double ret = 0;
		double repCt = expr.execute(log);
		List<Executable> makeRepCt = new ArrayList<Executable>();
		Variable var = new Variable(":repcount", null);
		Constant ct = new Constant("RepCount", 1);
		for (double i = 1; i <= repCt; i++) {
			ct.setValue(i);
			var.setExpression(ct);
			makeRepCt.add(ct);
			makeRepCt.add(var);
			Make myMake = new Make(makeRepCt);
			myMake.execute(log);
			makeRepCt.clear();
			var.execute(log);
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
