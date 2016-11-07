package model.token;

import java.util.Stack;

import model.GlobalVariables;
import model.ParserContext;
import model.SemanticsRegistry;
import model.executable.Constant;

/**
 * @author CharlesXu
 */
public class ConstantToken extends Token {

	public ConstantToken(String token, SemanticsRegistry semanticsRegistry) {
		super(token, semanticsRegistry);
	}

	@Override
	public void resolve(
			Stack<ParserContext> contextStack,
			GlobalVariables globalVars,
			Stack<Token> tokenStack) {
		contextStack.peek().getPendingArgs().add(new Constant(this));
	}
}
