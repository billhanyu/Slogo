package model.executable.stdCommand.controlFlow;

import java.util.List;

import exception.SyntacticErrorException;
import model.Executable;
import model.TurtleLog;
import model.executable.CodeBlock;
import model.executable.Constant;
import model.executable.StandardCommand;

public class To extends StandardCommand {
	
	private String stubName;
	private CodeBlock varList;
	private CodeBlock body;

	public To(List<Executable> argv)
			throws SyntacticErrorException {
		super(argv);
	}

	@Override
	public double execute(TurtleLog log)
			throws SyntacticErrorException {
		body.getSemantics().getImpl(stubName)
						   .init(varList, body);
		return 1;
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
		stubName = this.getArgs().get(0).getName();
		varList = (CodeBlock) this.getArgs().get(1);
		body = (CodeBlock) this.getArgs().get(2);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getName()).append(SPACE)
		  .append(varList.toString()).append(SPACE)
		  .append(body.toString()).append(SPACE);
		return sb.toString();
	}
}
