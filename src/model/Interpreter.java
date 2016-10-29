package model;

import java.util.Stack;

import exception.SyntacticErrorException;
import exception.UnrecognizedIdentifierException;
import exception.WrongNumberOfArguments;
import model.executable.CodeBlock;
import model.token.Token;
import model.token.TokenFactory;
import util.Utils;

public class Interpreter {
	
	public static final String SPACE_REGEX = "\\s+";
	public static final String TO = "to";
	
	private GlobalVariables globalVars;
	private UserCommands userCommands;
	private SemanticsRegistry semanticsRegistry;
	private TokenFactory tokenFactory;
	private Translator translator;
	
	public Interpreter() {
		globalVars = new GlobalVariables();
		userCommands = new UserCommands();
		semanticsRegistry = new SemanticsRegistry(userCommands);
		tokenFactory = new TokenFactory(semanticsRegistry);
		translator = new Translator();
	}
	
	/**
	 * Parse a SLOGO script and generate a CodeBlock to be executed
	 * @param script
	 * @return
	 * @throws UnrecognizedIdentifierException
	 * @throws WrongNumberOfArguments
	 * @throws SyntacticErrorException
	 */
	public CodeBlock parseScript(String script)
			throws UnrecognizedIdentifierException, WrongNumberOfArguments,
				   SyntacticErrorException {
		script = Utils.senitize(script);
		String translated = translator.translate(script, SPACE_REGEX);
		semanticsRegistry.register(translated);
		Stack<Token> tokenStack = tokenize(translated);
		CodeBlock main = buildMain(tokenStack);
		main.setName(script);
		return main;
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
	
	public void setLanguage(String language) {
		translator.setLanguage(language);
	}
	
	public Translator getTranslator() {
		return translator;
	}
	
	public UserCommands getUserCommands() {
		return userCommands;
	}
	
	public GlobalVariables getGlobalVars() {
		return globalVars;
	}
}
