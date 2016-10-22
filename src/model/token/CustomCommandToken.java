package model.token;

import java.util.List;
import java.util.Stack;

import exception.UnrecognizedIdentifierException;
import exception.WrongNumberOfArguments;
import model.Executable;
import model.GlobalVariables;
import model.ParserContext;
import model.SemanticsRegistry;
import model.executable.Constant;
import model.executable.ProcedureStub;

public class CustomCommandToken extends StandardCommandToken{
	
	public static final String TO = "to";

	public CustomCommandToken(String token, SemanticsRegistry semanticsRegistry) {
		super(token, semanticsRegistry);
	}

	@Override
	public void resolve(
			Stack<ParserContext> contextStack,
			GlobalVariables globalVars,
			Stack<Token> tokenStack) throws WrongNumberOfArguments,
											   UnrecognizedIdentifierException {
		if (!tokenStack.isEmpty()
				&& tokenStack.peek().toString().equals(TO)) {
			List<Executable> pendingArgs = contextStack.peek().getPendingArgs();
			pendingArgs.add(new Constant(token.toString()));
		} else {
			super.resolve(contextStack, globalVars, tokenStack);
			((ProcedureStub)cmd).setSemantics(semanticsRegistry);
		}
	}
}
