package model.executable.stdCommand.controlFlow;

import java.util.List;

import exception.SyntacticErrorException;
import model.Executable;
import model.TurtleLog;
import model.executable.CodeBlock;
import model.executable.Constant;
import model.executable.StandardCommand;

public class To extends StandardCommand {

	public To(List<Executable> argv)
			throws SyntacticErrorException {
		super(argv);
	}

	@Override
	public double execute(TurtleLog log)
			throws SyntacticErrorException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getName() {
		return "to";
	}
	
	@Override
	protected void validateArgv() throws SyntacticErrorException {
		if (!(this.getArgs().get(0) instanceof Constant)
				|| !(this.getArgs().get(1) instanceof CodeBlock)
				|| !(this.getArgs().get(2) instanceof CodeBlock)) {
			throw new SyntacticErrorException();
		}		
	}
}
