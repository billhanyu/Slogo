package model;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Stack;

import exception.ReflectionFoundNoMatchesException;
import exception.SyntacticException;
import exception.UnrecognizedIdentifierException;
import model.executable.Command;
import model.executable.Constant;
import util.ReflectionUtils;

public class Interpreter {
	
	public static final String TOKEN_DICT = "resources/tokens";
	public static final String PROP_CLASS = ".class";
	public static final String SPACE_REGEX = "\\s+";
	
	private ResourceBundle lexicon;
	
	public Interpreter() {
		lexicon = ResourceBundle.getBundle(TOKEN_DICT);
	}
	
	public List<Command> parseScript(String script)
			throws UnrecognizedIdentifierException, SyntacticException {
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
	
	// TODO (cx15): FIX THE CASE OF NESTED COMMANDS LIKE "FD FD 10"
	private List<Command> buildMain(Stack<String> tokenStack)
			throws UnrecognizedIdentifierException, SyntacticException {
		Stack<Command> commandStack = new Stack<>();
		List<Executable> argsTmp = new ArrayList<>();
		while (!tokenStack.isEmpty()) {
			String token = tokenStack.pop().toLowerCase();
			if (token.matches(lexicon.getString("constant.regex"))) {
				argsTmp.add(new Constant(Double.parseDouble(token)));
			} else if (token.matches(lexicon.getString("variable.regex"))) {
				// TODO (cx15): HANDLE VARS
			} else {
				try{
					String className = lexicon.getString(token + PROP_CLASS);
					Class<?> c = Class.forName(className);
					Constructor<?> constructor = ReflectionUtils.getConstructor(c, argsTmp);
					Command cmd = (Command) constructor.newInstance(argsTmp);
					commandStack.push(cmd);
					argsTmp = new ArrayList<>();
				} catch (MissingResourceException | ClassNotFoundException | InstantiationException
						| IllegalAccessException | IllegalArgumentException | InvocationTargetException | ReflectionFoundNoMatchesException e) {
					throw new UnrecognizedIdentifierException();
				} catch(SecurityException e) {
					throw new SyntacticException();
				}
			}
		}
		return revertOrderToList(commandStack);
	}
	
	private List<Command> revertOrderToList(Stack<Command> stk) {
		List<Command> main = new ArrayList<>();
		while (!stk.isEmpty()) {
			main.add(stk.pop());
		}
		return main;
	}
	
	public static void main(String[] args) {
		String s = "1";
		ResourceBundle l = ResourceBundle.getBundle(TOKEN_DICT);
		System.out.println(s.matches(l.getString("constant.regex")));
	}
}
