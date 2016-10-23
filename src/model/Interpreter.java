package model;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

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
	private Translator translator;
	
	public Interpreter() {
		// TODO (cx15): passed in a reference of globalVars
		globalVars = new GlobalVariables();
		semanticsRegistry = new SemanticsRegistry();
		tokenFactory = new TokenFactory(semanticsRegistry);
		translator = new Translator();
	}
	
	public CodeBlock parseScript(String script)
			throws UnrecognizedIdentifierException, WrongNumberOfArguments,
				   SyntacticErrorException {
		script = script.trim().replaceAll(" +", " ");
		script = translateScript(script);
		semanticsRegistry.register(script);
		Stack<Token> tokenStack = tokenize(script);
		return buildMain(tokenStack);
	}
	
	public void setLanguage(String language) {
		translator.setLanguage(language);
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
	
	private String translateScript(String script) {
		List<String> tokens = Arrays.asList(script.split(SPACE_REGEX));
		return tokens.stream()
				.map(token -> translator.translateToken(token))
				.collect(Collectors.joining(" "));
	}
	
}
