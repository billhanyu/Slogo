package model.token;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
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
			List<Executable> args = argsGen(numArgs, contextStack, pendingArgs, instructionCacheInReverse);
			this.cmd = (Command) ReflectionUtils.newInstanceOf(className, args);
			cmd.setName(token.toString());
			if (contextStack.peek().getPendingArgs().isEmpty())
				instructionCacheInReverse.add(this.cmd);
			else contextStack.peek().getPendingArgs().add(this.cmd);
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException| InvocationTargetException
				| ClassNotFoundException | ReflectionFoundNoMatchesException e) {
			throw new UnrecognizedIdentifierException();
		}
	}
	
	private List<Executable> argsGen(int numArgs,
								  	 Stack<ParserContext> contextStack, 
									 List<Executable> pendingArgs,
									 List<Executable> instructionCacheInReverse)
											 throws WrongNumberOfArguments {
		
		if (instructionCacheInReverse.size() + pendingArgs.size() < numArgs) {
			throw new WrongNumberOfArguments();
		}
		List<Executable> args;
		if (pendingArgs.size() < numArgs) {
			args = new ArrayList<>(pendingArgs);
			moveToArgs(args, instructionCacheInReverse, numArgs);
			contextStack.peek().clearPendingArgs();
		} else {
			args = new ArrayList<>();
			moveToArgs(args, pendingArgs, numArgs);
		}
		return args;
	}
	
	private void moveToArgs(List<Executable> args, List<Executable> list, int numArgs) {
		for (int i = list.size() - 1; args.size() < numArgs; i--) {
			args.add(list.remove(i));
		}
	}
}
