package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import exception.SyntacticErrorException;
import exception.UnrecognizedIdentifierException;
import exception.WrongNumberOfArguments;
import model.ActorState;
import model.Interpreter;
import model.TurtleLog;
import model.TurtleState;
import model.executable.CodeBlock;


public class InterpreterTest {
	
	public static final double EPSILON = 0.1;
	
	private TurtleLog log;
	private Interpreter intr;
	
	@Before
	public void executedOnceBeforeEach() {
		log = new TurtleLog();
		ActorState state = new TurtleState();
		log.append(state);
		intr = new Interpreter();
	}
	
	@Test
	public void forwardForward() {
		parseAndExecute("fd fd fd 10");
		assertDoubleEqual(log.peekLast().getPositionX(), 30);
	}
	
	@Test
	public void backBack() {
		parseAndExecute("bk back 10");
		assertDoubleEqual(log.peekLast().getPositionX(), -20);
	}
	
	@Test
	public void leftLeft() {
		parseAndExecute("lt left 10");
		assertDoubleEqual(log.peekLast().getHeading(), 20);
	}
	
	@Test
	public void rightRight() {
		parseAndExecute("rt right 10");
		assertDoubleEqual(log.peekLast().getHeading(), -20);
	}
	
	@Test
	public void setHeading() {
		parseAndExecute("seth 60.5");
		assertDoubleEqual(log.peekLast().getHeading(), 60.5);
	}

	@Test
	public void Towards() {
		parseAndExecute("towards 1 0");
		assertDoubleEqual(log.peekLast().getHeading(), 0);
	}
	
	@Test
	public void SetXY() {
		parseAndExecute("setxy 10 5"); //fails when changed to goto?
		assertDoubleEqual(log.peekLast().getPositionX(), 10);
		assertDoubleEqual(log.peekLast().getPositionY(), 5);
	}
	
	@Test
	public void PenDown() {
		parseAndExecute("pd");
		assertTrue(log.peekLast().isPenDown());
	}
	
	@Test
	public void PenUp() {
		parseAndExecute("pu");
		assertTrue(!log.peekLast().isPenDown());
	}
	
	@Test
	public void ShowTurtle() {
		parseAndExecute("st");
		assertTrue(log.peekLast().isVisible());
	}
	
	@Test
	public void Home() {
		parseAndExecute("setxy 10 5 home");
		assertDoubleEqual(log.peekLast().getPositionX(), 0);
		assertDoubleEqual(log.peekLast().getPositionY(), 0);
	}
	
	@Test
	public void ClearScreen() { //same as home, no trail clearing implemented
		parseAndExecute("setxy 10 5 cs");
		assertDoubleEqual(log.peekLast().getPositionX(), 0);
		assertDoubleEqual(log.peekLast().getPositionY(), 0);
	}
	
	@Test
	public void HideTurtle() {
		parseAndExecute("ht");
		assertTrue(!log.peekLast().isVisible());
	}
	
	@Test
	public void makeVar() {
		parseAndExecute("make :dist 10 fd :dist");
		assertDoubleEqual(log.peekLast().getPositionX(), 10);
	}
	
	
	private void parseAndExecute(String script) {
		try {
			CodeBlock main = intr.parseScript(script);
			main.execute(log);
		} catch (UnrecognizedIdentifierException | WrongNumberOfArguments
				| SyntacticErrorException e) {
			assertNull(e);
		}
	}
	
	private void assertDoubleEqual(double d1, double d2) {
		assertTrue(Math.abs(d1 - d2) < EPSILON);
	}
}
