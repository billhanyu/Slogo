package model.executable.stdCommand.controlFlow;

import java.util.List;

import exception.SyntacticErrorException;
import model.Executable;
import model.TurtleLog;
import model.executable.StandardCommand;
import model.executable.Variable;

public class Make extends StandardCommand{
	
	public Make(List<Executable> argv)
			throws SyntacticErrorException {
		super(argv);
	}

	@Override
	public double execute(TurtleLog log)
			throws SyntacticErrorException {
		Variable var = (Variable) argv.get(0);
		var.setExpression(argv.get(1));
		return var.execute(log);
	}

	@Override
	public String getName() {
		return "make";
	}
	
	@Override
	protected void validateArgv()
			throws SyntacticErrorException {
		if ( !(argv.get(0) instanceof Variable) )
			throw new SyntacticErrorException();
	}
}
