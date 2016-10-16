package model;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Stack;

import exception.ReflectionFoundNoMatchesException;
import exception.UnrecognizedIdentifierException;
import exception.WrongNumberOfArguments;
import model.executable.Command;
import model.executable.Constant;
import util.ReflectionUtils;

public class Interpreter {
	
	public static final String TOKEN_DICT = "resources/tokens";
	public static final String PROP_CLASS = ".class";
	public static final String PROP_ARGC = ".argc";
	public static final String SPACE_REGEX = "\\s+";
	
	private ResourceBundle lexicon;
	
	public Interpreter() {
		lexicon = ResourceBundle.getBundle(TOKEN_DICT);
	}
	
	public List<Command> parseScript(String script)
			throws UnrecognizedIdentifierException, WrongNumberOfArguments {
		script = script.trim();
		Stack<String> tokenStack = tokenize(script);
		return buildMain(tokenStack);
	}
	
	private Stack<String> tokenize(String script) {
		String[] tokens = script.split(SPACE_REGEX);
		Stack<String> tokenStack = new Stack<>();
		for (String token : tokens)
			tokenStack.push(token);
		return tokenStack;
	}
	
	private List<Command> buildMain(Stack<String> tokenStack)
			throws UnrecognizedIdentifierException, WrongNumberOfArguments {
		List<Command> instructionCacheInReverse = new ArrayList<>();
		List<Executable> pendingArgs = new ArrayList<>();
		while (!tokenStack.isEmpty()) {
			String token = tokenStack.pop().toLowerCase();
			if (token.matches(lexicon.getString("constant.regex"))) {
				pendingArgs.add(new Constant(Double.parseDouble(token)));
			} else if (token.matches(lexicon.getString("variable.regex"))) {
				// TODO (cx15): HANDLE VARS
			} else {
				try{
					String className = lexicon.getString(token + PROP_CLASS);
					int numArgs = Integer.parseInt(lexicon.getString(token + PROP_ARGC));
					Class<?> c = Class.forName(className);
					pendingArgs = argsGen(numArgs, c, pendingArgs, instructionCacheInReverse);
					Constructor<?> constructor = ReflectionUtils.getConstructor(c, pendingArgs);
					Command cmd = (Command) constructor.newInstance(pendingArgs);
					instructionCacheInReverse.add(cmd);
					pendingArgs = new ArrayList<>();
				} catch (ClassNotFoundException | ReflectionFoundNoMatchesException
						| InstantiationException | IllegalAccessException
						| IllegalArgumentException | InvocationTargetException e) {
					throw new UnrecognizedIdentifierException();
				} 
			}
		}
		Collections.reverse(instructionCacheInReverse);
		return instructionCacheInReverse;
	}
	
	private List<Executable> argsGen(int numArgs, Class<?> c, 
									 List<Executable> pendingArgs,
									 List<Command> instructionCacheInReverse)
											 throws WrongNumberOfArguments {
		if (pendingArgs.size() > numArgs) {
			throw new WrongNumberOfArguments(c.getName());
		} else if (pendingArgs.size() < numArgs) {
			if (instructionCacheInReverse.size() + pendingArgs.size() < numArgs) {
				throw new WrongNumberOfArguments(c.getName());
			} else {
				for (int i = instructionCacheInReverse.size()-1; pendingArgs.size() < numArgs; i--) {
					pendingArgs.add(instructionCacheInReverse.get(i));
				}
			}
		}
		return pendingArgs;
	}
}
