package model.executable.stdCommand.controlFlow;

import java.util.List;

import exception.SyntacticErrorException;
import model.Executable;
import model.TurtleLog;
import model.executable.StandardCommand;
import model.executable.Variable;

public class Make extends StandardCommand{

	public Make(List<Executable> argv) {
		super(argv);
	}

	@Override
	public double execute(TurtleLog log)
			throws SyntacticErrorException {
		if ( !(argv.get(0) instanceof Variable) )
			throw new SyntacticErrorException();
		Variable var = (Variable) argv.get(0);
		var.setExpression(argv.get(1));
		return var.execute(log);
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

}
