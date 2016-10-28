package model.token;

import java.util.Stack;

import model.GlobalVariables;
import model.ParserContext;
import model.SemanticsRegistry;

public class CloseBracketToken extends Token {

	public CloseBracketToken(String token, SemanticsRegistry semanticsRegistry) {
		super(token, semanticsRegistry);
	}

	@Override
	public void resolve(
			Stack<ParserContext> contextStack,
			GlobalVariables globalVars,
			Stack<Token> tokenStack) {
		contextStack.push(new ParserContext());
	}
}
