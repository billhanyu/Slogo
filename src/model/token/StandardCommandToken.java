package model.token;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import exception.ReflectionFoundNoMatchesException;
import exception.UnrecognizedIdentifierException;
import exception.WrongNumberOfArguments;
import model.Executable;
import model.GlobalVariables;
import model.ParserContext;
import model.SemanticsRegistry;
import model.executable.Command;
import util.ReflectionUtils;

public class StandardCommandToken extends Token {
	
	protected Command cmd;

	public StandardCommandToken(
			String token, SemanticsRegistry semanticsRegistry) {
		super(token, semanticsRegistry);
	}

	@Override
	public void resolve(
			Stack<ParserContext> contextStack,
			GlobalVariables globalVars,
			Stack<Token> tokenStack) throws WrongNumberOfArguments,
										    UnrecognizedIdentifierException {
		try {
			String className = semanticsRegistry.getClass(this);
			int numArgs = semanticsRegistry.getNumParam(this);
			List<Executable> pendingArgs = contextStack.peek().getPendingArgs();
			List<Executable> instructionCacheInReverse
					= contextStack.peek().getInstructionCacheInReverse();
			pendingArgs = argsGen(numArgs, className, pendingArgs, instructionCacheInReverse);
			this.cmd = (Command) ReflectionUtils.newInstanceOf(className, pendingArgs);
			cmd.setName(token.toString());
			instructionCacheInReverse.add(this.cmd);
			contextStack.peek().clearPendingArgs();
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException| InvocationTargetException
				| ClassNotFoundException | ReflectionFoundNoMatchesException e) {
			throw new UnrecognizedIdentifierException();
		}
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
				for (int i = instructionCacheInReverse.size() - 1; pendingArgs.size() < numArgs; i--) {
					pendingArgs.add(instructionCacheInReverse.remove(i));
				}
				Collections.reverse(pendingArgs);
			}
		}
		return pendingArgs;
	}
}
