package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import exception.UnrecognizedIdentifierException;
import exception.WrongNumberOfArguments;
import model.ActorState;
import model.Executable;
import model.Interpreter;
import model.TurtleLog;
import model.TurtleState;
import model.executable.Command;
import model.executable.Constant;
import model.executable.stdCommand.movement.Forward;


public class CommandTest {
	
	private TurtleLog log;
	
	@Before
	public void executedOnceBeforeEach() {
		log = new TurtleLog();
		ActorState state = new TurtleState();
		log.append(state);
	}

	@Test
	public void forwardForward() {
		List<Executable> args1 = new ArrayList<>();
		args1.add(new Constant(10));
		Forward f1 = new Forward(args1);
		List<Executable> args2 = new ArrayList<>();
		args2.add(f1);
		Forward f2 = new Forward(args2);
		f2.execute(log);
		assertTrue(log.peekLast().getPositionX() - 20 < 0.1);
	}
	
	@Test
	public void forwardForwardUsingInterpreter() {
		Interpreter intr = new Interpreter();
		try {
			List<Command> main = intr.parseScript("fd fd 10");
			for (Command cmd : main) {
				cmd.execute(log);
			}
			assertTrue(log.peekLast().getPositionX() - 20 < 0.1);
		} catch (UnrecognizedIdentifierException | WrongNumberOfArguments e) {
			assertNull(e);
		}
	}
}
