package model.executable.stdCommand.controlFlow;

import java.util.List;

import exception.SyntacticErrorException;
import model.Executable;
import model.TurtleLog;
import model.executable.StandardCommand;
import model.executable.Variable;

public class Make extends StandardCommand{

	public Make(List<Executable> argv) {
		// argv == {constant, variable}
		super(argv);
	}

	@Override
	public double execute(TurtleLog log)
			throws SyntacticErrorException {
		// assign argv[0] to argv[1].value
		if ( !(argv.get(1) instanceof Variable) )
			throw new SyntacticErrorException();
		Variable var = (Variable) argv.get(1);
		var.setExpression(argv.get(0));
		return var.execute(log);
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

}
