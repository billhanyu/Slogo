package model;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import exception.ReflectionFoundNoMatchesException;
import exception.SyntacticErrorException;
import exception.UnrecognizedIdentifierException;
import exception.WrongNumberOfArguments;
import model.executable.CodeBlock;
import model.executable.Command;
import model.executable.Constant;
import model.executable.ProcedureStub;
import model.executable.Variable;
import util.ReflectionUtils;

public class Interpreter {
	
	public static final String SPACE_REGEX = "\\s+";
	public static final String TO = "to";
	
	private GlobalVariables globalVars;
	private SemanticsRegistry semanticsRegistry;
	
	public Interpreter() {
		// TODO (cx15): passed in a reference of globalVars
		globalVars = new GlobalVariables();
		semanticsRegistry = new SemanticsRegistry();
	}
	
	public CodeBlock parseScript(String script)
			throws UnrecognizedIdentifierException, WrongNumberOfArguments,
				   SyntacticErrorException {
		script = script.trim().replaceAll(" +", " ");
		semanticsRegistry.register(script);
		Stack<Token> tokenStack = tokenize(script);
		return buildMain(tokenStack);
	}
	
	private Stack<Token> tokenize(String script) {
		script = script.toLowerCase();
		String[] tokens = script.split(SPACE_REGEX);
		Stack<Token> tokenStack = new Stack<>();
		for (String token : tokens)
			tokenStack.push(new Token(token, semanticsRegistry));
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
			List<Executable> pendingArgs = contextStack.peek().getPendingArgs();
			List<Executable> instructionCacheInReverse
					= contextStack.peek().getInstructionCacheInReverse();
			GlobalVariables vars = contextStack.peek().getVars();
			if (token.isOpenBracket()) {
				CodeBlock cb = contextStack.pop().export();
				cb.setVarRefs(vars, globalVars)
				  .setSemantics(semanticsRegistry);
				contextStack.peek().getPendingArgs().add(cb);
			} else if (token.isCloseBracket()) {
				contextStack.push(new ParserContext());
			} else if (token.isConstant()) {
				pendingArgs.add(new Constant(token));
			} else if (token.isVariable()) {
				Variable var;
				if ( (var = vars.get(token)) == null) {
					vars.add(var = new Variable(token));
				}
				pendingArgs.add(var);
			} else if (token.isCustomCommand()
						&& !tokenStack.isEmpty()
						&& tokenStack.peek().toString().equals(TO)) {
					pendingArgs.add(new Constant(token.toString()));
					continue; // skip over next branch
			} else if (token.isStdCommand() || token.isCustomCommand()) {
				try{
					String className = semanticsRegistry.getClass(token);
					int numArgs = semanticsRegistry.getNumParam(token);
					Class<?> c = Class.forName(className);
					pendingArgs = argsGen(numArgs, className, pendingArgs, instructionCacheInReverse);
					Constructor<?> constructor = ReflectionUtils.getConstructor(c, pendingArgs);
					Command cmd = (Command) constructor.newInstance(pendingArgs);
					if (token.isCustomCommand()) {
						((ProcedureStub)cmd).setSemantics(semanticsRegistry);
					}
					cmd.setName(token.toString());
					instructionCacheInReverse.add(cmd);
					contextStack.peek().clearPendingArgs();
				} catch (ClassNotFoundException | ReflectionFoundNoMatchesException
						| InstantiationException | IllegalAccessException
						| IllegalArgumentException | InvocationTargetException e) {
					throw new UnrecognizedIdentifierException();
				} 
			} else {
				throw new UnrecognizedIdentifierException();
			}
		}
		if (contextStack.size() != 1) {
			throw new SyntacticErrorException("Miss matched brackets");
		}
		return contextStack.peek().export()
				  .setVarRefs(globalVars, globalVars)
				  .setSemantics(semanticsRegistry);
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
				for (int i = instructionCacheInReverse.size()-1;
						pendingArgs.size() < numArgs;
						i--) {
					pendingArgs.add(instructionCacheInReverse.remove(i));
				}
				Collections.reverse(pendingArgs);
			}
		}
		return pendingArgs;
	}
}
