package model;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
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
import model.executable.Variable;
import util.ReflectionUtils;

public class Interpreter {
	
	public static final String SPACE_REGEX = "\\s+";
	
	private GlobalVariables globalVars;
	private SemanticsRegistry semanticsRegistry;
	
	public Interpreter() {
		// TODO (cx15): passed a reference of globalVars
		globalVars = new GlobalVariables();
		semanticsRegistry = new SemanticsRegistry();
	}
	
	public CodeBlock parseScript(String script)
			throws UnrecognizedIdentifierException, WrongNumberOfArguments,
				   SyntacticErrorException {
		script = script.trim().replaceAll(" +", " ");
		semanticsRegistry.register(script);
		Stack<Token> tokenStack = tokenize(script);
		// TODO (cx15): preprocess to construct all procedure impl first since don't know param len
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
			throws UnrecognizedIdentifierException, WrongNumberOfArguments {
		Stack<ParserContext> contextStack = new Stack<>();
		contextStack.push(new ParserContext());
		while (!tokenStack.isEmpty()) {
			Token token = tokenStack.pop();
			List<Executable> pendingArgs = contextStack.peek().getPendingArgs();
			List<Executable> instructionCacheInReverse = contextStack.peek().getInstructionCacheInReverse();
//			if (token.isOpenBracket()) {
//				codeBlocks.push(new ArrayList<>());
//			} else if (token.isCloseBracket()) {
//				CodeBlock cb = new CodeBlock(codeBlocks.pop());
//				pendingArgs.add(cb);
//			}
			if (token.isConstant()) {
				pendingArgs.add(new Constant(token));
			} else if (token.isVariable()) {
				//TODO cx15: USE addVarRef() HERE TO HANDLE CODEBLOCK
				if (globalVars.get(token.toString()) == null) {
					Variable var = new Variable(token);
					globalVars.add(var);
				}
				pendingArgs.add(globalVars.get(token.toString()));
			} else if (token.isStdCommand() || token.isCustomCommand()) {
				try{
					String className = semanticsRegistry.getClass(token);
					int numArgs = semanticsRegistry.getNumParam(token);
					Class<?> c = Class.forName(className);
					pendingArgs = argsGen(numArgs, className, pendingArgs, instructionCacheInReverse);
					Constructor<?> constructor = ReflectionUtils.getConstructor(c, pendingArgs);
					Command cmd = (Command) constructor.newInstance(pendingArgs);
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
		Collections.reverse(contextStack.peek().getInstructionCacheInReverse());
		return new CodeBlock(contextStack.peek().getInstructionCacheInReverse());
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
