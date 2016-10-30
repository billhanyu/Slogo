package model.executable.singleCommand.controlFlow;

import java.util.List;

import exception.SyntacticErrorException;
import model.Executable;
import model.LogHolder;
import model.executable.SingleCommand;
import model.executable.Variable;

public class Make extends SingleCommand{
	
	public Make(List<Executable> argv)
			throws SyntacticErrorException {
		super(argv);
	}

	@Override
	public double execute(LogHolder log)
			throws SyntacticErrorException {
		Variable var = (Variable) this.getArgs().get(0);
		var.setExpression(this.getArgs().get(1).execute(log));
		return var.execute(log);
	}

	@Override
	public String getName() {
		return "make";
	}
	
	@Override
	protected void validateArgv()
			throws SyntacticErrorException {
		if ( !(this.getArgs().get(0) instanceof Variable) )
			throw new SyntacticErrorException();
	}
}
