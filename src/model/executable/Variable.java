package model.executable;

import exception.SyntacticErrorException;
import exception.UseBeforeDefineException;
import model.Executable;
import model.TurtleLog;
import model.token.Token;

public class Variable implements Executable {
	
	private String name;
	private Executable expression;
	
	public Variable(Token token) {
		this(token.toString(), null);
	}
	
	public Variable(String name, Executable expression) {
		this.name = name;
		this.expression = expression;
	}
	
	public Variable setExpression(Executable expression) {
		this.expression = expression;
		return this;
	}
	
	public Variable setExpression(double value) {
		this.expression = new Constant(value);
		return this;
	}
	
	public Executable getExpression() {
		return expression;
	}
	
	@Override
	public double execute(TurtleLog log)
			throws SyntacticErrorException {
		if (expression == null)
			throw new UseBeforeDefineException();
		return expression.execute(log);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String newName) {
		name = newName;		
	}
}
