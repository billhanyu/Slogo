package model.token;

import java.util.Stack;

import model.GlobalVariables;
import model.ParserContext;
import model.SemanticsRegistry;
import model.executable.Variable;

/**
 * @author CharlesXu
 */
public class VariableToken extends Token {

	public VariableToken(String token, SemanticsRegistry semanticsRegistry) {
		super(token, semanticsRegistry);
	}

	@Override
	public void resolve(
			Stack<ParserContext> contextStack,
			GlobalVariables globalVars,
			Stack<Token> tokenStack) {
		GlobalVariables vars = contextStack.peek().getVars();
		Variable var;
		if ( (var = vars.get(this)) == null) {
			vars.add(var = new Variable(this));
		}
		contextStack.peek().getPendingArgs().add(var);
	}
}
