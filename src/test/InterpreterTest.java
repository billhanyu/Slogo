package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import exception.SyntacticErrorException;
import exception.UnrecognizedIdentifierException;
import exception.WrongNumberOfArguments;
import model.ActorState;
import model.Executable;
import model.Interpreter;
import model.TurtleLog;
import model.TurtleState;
import model.executable.CodeBlock;
import model.executable.Command;
import model.executable.Constant;
import model.executable.stdCommand.movement.Forward;


public class InterpreterTest {
	
	public static final double EPSILON = 0.1;
	
	private TurtleLog log;
	private Interpreter intr;
	
	@Before
	public void executedOnceBeforeEach() {
		log = new TurtleLog();
		ActorState state = new TurtleState();
		intr = new Interpreter();
		log.append(state);
	}
	
	@Test
	public void forwardForward() {
		parseAndExecute("fd fd 10");
		assertDoubleEqual(log.peekLast().getPositionX(), 20);
	}
	
	@Test
	public void makeVar() {
		parseAndExecute("make :dist 10 fd :dist");
		assertDoubleEqual(log.peekLast().getPositionX(), 10);
	}
	
	private void parseAndExecute(String script) {
		try {
			CodeBlock main = intr.parseScript("fd fd 10");
			main.execute(log);
			assertTrue(log.peekLast().getPositionX() - 20 < 0.1);
		} catch (UnrecognizedIdentifierException | WrongNumberOfArguments
				| SyntacticErrorException e) {
			assertNull(e);
		}
	}
	
	private void assertDoubleEqual(double d1, double d2) {
		assertTrue(d1 - d2 < EPSILON);
	}
}
