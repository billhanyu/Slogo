package model.token;

import java.util.List;
import java.util.Stack;

import model.Executable;
import model.GlobalVariables;
import model.ParserContext;
import model.SemanticsRegistry;
import model.executable.CodeBlock;

public class OpenBracketToken extends Token {

	public OpenBracketToken(String token, SemanticsRegistry semanticsRegistry) {
		super(token, semanticsRegistry);
	}

	@Override
	public void resolve(
			Stack<ParserContext> contextStack,
			GlobalVariables globalVars,
			Stack<Token> tokenStack) {
		GlobalVariables localVars = contextStack.peek().getVars();
		List<Executable> pendingArgs = contextStack.peek().getPendingArgs();
		CodeBlock cb = contextStack.pop().export();
		cb.setVarRefs(localVars, globalVars)
		  .setPendingArgs(pendingArgs)
		  .setSemantics(semanticsRegistry);
		contextStack.peek().getVars().addAll(localVars);
		contextStack.peek().getPendingArgs().add(cb);
	}
}
