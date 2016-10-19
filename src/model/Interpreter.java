package model;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Stack;

import exception.ReflectionFoundNoMatchesException;
import exception.SyntacticErrorException;
import exception.UnrecognizedIdentifierException;
import exception.WrongNumberOfArguments;
import model.executable.CodeBlock;
import model.executable.Command;
import model.executable.Constant;
import model.executable.Variable;
import util.ReflectionUtils;

public class Interpreter {
	
	public static final String TOKEN_DICT = "resources/tokens";
	public static final String PROP_CLASS = ".class";
	public static final String PROP_ARGC = ".argc";
	public static final String SPACE_REGEX = "\\s+";
	
	public static final int VAR_EXPR_LEN = 1;
	
	private ResourceBundle lexicon;
	private StackFrame globalVars;
	private SemanticsRegistry semanticsRegistry;
	
	public Interpreter() {
		lexicon = ResourceBundle.getBundle(TOKEN_DICT);
		globalVars = new StackFrame();
		semanticsRegistry = new SemanticsRegistry();
	}
	
	public CodeBlock parseScript(String script)
			throws UnrecognizedIdentifierException, WrongNumberOfArguments,
				   SyntacticErrorException {
		script = script.trim().replaceAll(" +", " ");
		semanticsRegistry.register(script);
		Stack<String> tokenStack = tokenize(script);
		// TODO (cx15): preprocess to construct all procedure impl first since don't know param len
		return buildMain(tokenStack);
	}
	
	private Stack<String> tokenize(String script) {
		script = script.toLowerCase();
		String[] tokens = script.split(SPACE_REGEX);
		Stack<String> tokenStack = new Stack<>();
		for (String token : tokens)
			tokenStack.push(token);
		return tokenStack;
	}
	
	private CodeBlock buildMain(Stack<String> tokenStack)
			throws UnrecognizedIdentifierException, WrongNumberOfArguments {
		List<Executable> instructionCacheInReverse = new ArrayList<>();
		List<Executable> pendingArgs = new ArrayList<>();
		while (!tokenStack.isEmpty()) {
			String token = tokenStack.pop();
			if (semanticsRegistry.isConstant(token)) {
				pendingArgs.add(new Constant(Double.parseDouble(token)));
			} else if (semanticsRegistry.isVariable(token)) {
				if (globalVars.get(token) == null) {
					Variable var = new Variable(token);
					globalVars.add(var);
				}
				pendingArgs.add(globalVars.get(token));
			} else if (semanticsRegistry.isStdCommand(token) || semanticsRegistry.isCustomCommand(token)) {
				try{
					String className = lexicon.getString(token + PROP_CLASS);
					int numArgs = Integer.parseInt(lexicon.getString(token + PROP_ARGC));
					Class<?> c = Class.forName(className);
					pendingArgs = argsGen(numArgs, className, pendingArgs, instructionCacheInReverse);
					Constructor<?> constructor = ReflectionUtils.getConstructor(c, pendingArgs);
					Command cmd = (Command) constructor.newInstance(pendingArgs);
					instructionCacheInReverse.add(cmd);
					pendingArgs = new ArrayList<>();
				} catch (ClassNotFoundException | ReflectionFoundNoMatchesException
						| InstantiationException | IllegalAccessException
						| IllegalArgumentException | InvocationTargetException e) {
					throw new UnrecognizedIdentifierException();
				} 
			} else {
				throw new UnrecognizedIdentifierException();
			}
		}
		Collections.reverse(instructionCacheInReverse);
		return new CodeBlock(instructionCacheInReverse);
	}
	
	private List<Executable> argsGen(int numArgs, String className, 
									 List<Executable> pendingArgs,
									 List<Executable> instructionCacheInReverse)
											 throws WrongNumberOfArguments {
		if (pendingArgs.size() > numArgs) {
			throw new WrongNumberOfArguments(className);
		} else if (pendingArgs.size() < numArgs) {
			if (instructionCacheInReverse.size() + pendingArgs.size() < numArgs) {
				throw new WrongNumberOfArguments(className);
			} else {
				for (int i = instructionCacheInReverse.size()-1; pendingArgs.size() < numArgs; i--) {
					pendingArgs.add(instructionCacheInReverse.remove(i));
				}
			}
		}
		return pendingArgs;
	}
}
