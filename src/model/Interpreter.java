package model;

import java.util.Stack;

import exception.SyntacticErrorException;
import exception.UnrecognizedIdentifierException;
import exception.WrongNumberOfArguments;
import model.executable.CodeBlock;
import model.token.Token;
import model.token.TokenFactory;

public class Interpreter {
	
	public static final String SPACE_REGEX = "\\s+";
	public static final String TO = "to";
	
	private GlobalVariables globalVars;
	private SemanticsRegistry semanticsRegistry;
	private TokenFactory tokenFactory;
	
	public Interpreter() {
		// TODO (cx15): passed in a reference of globalVars
		globalVars = new GlobalVariables();
		semanticsRegistry = new SemanticsRegistry();
		tokenFactory = new TokenFactory(semanticsRegistry);
	}
	
	public CodeBlock parseScript(String script)
			throws UnrecognizedIdentifierException, WrongNumberOfArguments,
				   SyntacticErrorException {
		script = script.trim().replaceAll(" +", " ");
		semanticsRegistry.register(script);
		Stack<Token> tokenStack = tokenize(script);
		return buildMain(tokenStack);
	}
	
	private Stack<Token> tokenize(String script)
			throws UnrecognizedIdentifierException {
		script = script.toLowerCase();
		String[] tokens = script.split(SPACE_REGEX);
		Stack<Token> tokenStack = new Stack<>();
		for (String token : tokens) {
			Token t = tokenFactory.build(token);
			tokenStack.push(t);
		}
		return tokenStack;
	}
	
	private CodeBlock buildMain(Stack<Token> tokenStack)
			throws UnrecognizedIdentifierException, WrongNumberOfArguments,
				   SyntacticErrorException {
		// TODO cx15: extends Token to have different subclasses
		Stack<ParserContext> contextStack = new Stack<>();
		contextStack.push(new ParserContext(globalVars));
		while (!tokenStack.isEmpty()) {
			Token token = tokenStack.pop();
			token.resolve(contextStack, globalVars, tokenStack);
		}
		if (contextStack.size() != 1) {
			throw new SyntacticErrorException("Miss matched brackets");
		}
		return contextStack.peek().export()
				  .setVarRefs(globalVars, globalVars)
				  .setSemantics(semanticsRegistry);
	}
}
