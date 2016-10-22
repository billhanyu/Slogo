package model.token;

import java.util.Stack;

import exception.UnrecognizedIdentifierException;
import exception.WrongNumberOfArguments;
import model.GlobalVariables;
import model.ParserContext;
import model.SemanticsRegistry;

public abstract class Token {
	
	protected String token;
	protected SemanticsRegistry semanticsRegistry;
	
	public Token(String token, SemanticsRegistry semanticsRegistry) {
		this.token = token;
		this.semanticsRegistry = semanticsRegistry;
	}
	
	public abstract void resolve(
			Stack<ParserContext> contextStack,
			GlobalVariables globalVars,
			Stack<Token> tokenStack
	) throws WrongNumberOfArguments, UnrecognizedIdentifierException;
	
//	public boolean isStdCommand() {
//		return semanticsRegistry.getStandardCommands().contains(token);
//	}
//	
//	public boolean isCustomCommand() {
//		return !isStdCommand() && semanticsRegistry.getNumParam(this) >= 0;
//	}
//	
//	public boolean isConstant() {
//		return matches("constant.regex");
//	}
//	
//	public boolean isVariable() {
//		return matches("variable.regex");
//	}
//	
//	public boolean isOpenBracket() {
//		return matches("openBracket.regex");
//	}
//	
//	public boolean isCloseBracket() {
//		return matches("closeBracket.regex");
//	}
	
	@Override
	public String toString() {
		return token;
	}
}
