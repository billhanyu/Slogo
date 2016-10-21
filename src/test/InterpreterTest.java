package test;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;


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
import util.Utils;


public class InterpreterTest {
	
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
	public void procedure() {
		parseAndExecute("to func [ :param ] [ fd :param ] func 10");
		assertDoubleEqual(log.peekLast().getPositionX(), 10);
	}
	/*
	@Test
	public void forwardForward() {
		parseAndExecute("fd fd fd 10");
		assertDoubleEqual(log.peekLast().getPositionY(), -30);
	}
	
	@Test
	public void backBack() {
		parseAndExecute("bk back 10");
		assertDoubleEqual(log.peekLast().getPositionY(), 20);
	}
	
	@Test
	public void leftLeft() {
		parseAndExecute("lt left 10");
		assertDoubleEqual(log.peekLast().getHeading(), -20);
	}
	
	@Test
	public void rightRight() {
		parseAndExecute("rt right 10");
		assertDoubleEqual(log.peekLast().getHeading(), 20);
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
		parseAndExecute("setxy 10 5");
		assertDoubleEqual(log.peekLast().getPositionX(), 10);
		assertDoubleEqual(log.peekLast().getPositionY(), 5);
		parseAndExecute("setxy 20 8");
		assertDoubleEqual(log.peekLast().getPositionX(), 20);
		assertDoubleEqual(log.peekLast().getPositionY(), 8);
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
		assertDoubleEqual(log.peekLast().getPositionY(), -10);
	}
	
	@Test
	public void checkMath() {
		double result;
		boolean thrown = false;
		result = parseAndExecute("sum 100 100");
		assertDoubleEqual(result, 200);
		result = parseAndExecute("sum -100 -100");
		assertDoubleEqual(result, -200);
		result = parseAndExecute("difference -100 100");
		assertDoubleEqual(result, -200);
		result = parseAndExecute("product 15 15");
		assertDoubleEqual(result, 225);
		result = parseAndExecute("quotient 5 sum 1 1");
		assertDoubleEqual(result, 2.5);
		try {
		    result = parseAndThrow("quotient 5 0");
		} catch (SyntacticErrorException e) {
			thrown = true;
		}
		assertTrue(thrown);
		thrown = false;
		result = parseAndExecute("% 15 / 16 + 2 ~ -2");
		assertDoubleEqual(result, 3);
		try {
		    result = parseAndThrow("remainder 5 0");
		} catch (SyntacticErrorException e) {
			thrown = true;
		}
		assertTrue(thrown);
		thrown = false;
		try {
		    result = parseAndThrow("random -1");
		} catch (SyntacticErrorException e) {
			thrown = true;
		}
		assertTrue(thrown);
		thrown = false;
		result = parseAndExecute("sin 30");
		assertDoubleEqual(result, 0.5);
		result = parseAndExecute("cos 60");
		assertDoubleEqual(result, 0.5);
		result = parseAndExecute("tan 45");
		assertDoubleEqual(result, 1);
		result = parseAndExecute("atan 1");
		assertDoubleEqual(result, 45);
		result = parseAndExecute("log 1");
		assertDoubleEqual(result, 0);
		result = parseAndExecute("pow 2 2");
		assertDoubleEqual(result, 4);
		result = parseAndExecute("pi");
		assertDoubleEqual(result, Math.PI);
	}
	*/
	
	@Test
	public void checkBool() {
		double result;
		result = parseAndExecute("lessp 2 3");
		assertDoubleEqual(result, 1);
		result = parseAndExecute("greater? 3 2");
		assertDoubleEqual(result, 1);
		result = parseAndExecute("equal? 3 3");
		assertDoubleEqual(result, 1);
		result = parseAndExecute("notequal? 3 3");
		assertDoubleEqual(result, 0);
		result = parseAndExecute("and 3 3");
		assertDoubleEqual(result, 1);
		result = parseAndExecute("or 1 0");
		assertDoubleEqual(result, 1);
		result = parseAndExecute("not 0");
		assertDoubleEqual(result, 1);
	}
	
	private double parseAndExecute(String script) {
		double result = 0;
		try {
			CodeBlock main = intr.parseScript(script);
			result = main.execute(log);
		} catch (UnrecognizedIdentifierException | WrongNumberOfArguments
				| SyntacticErrorException e) {
			assertNull(e);
		}
		return result;
	}
	
	private double parseAndThrow(String script) throws SyntacticErrorException {
		double result = 0;
		try {
			CodeBlock main = intr.parseScript(script);
			result = main.execute(log);
		} catch (UnrecognizedIdentifierException | WrongNumberOfArguments e) {
			assertNull(e);
		}
		return result;
	}
	
	private void assertDoubleEqual(double d1, double d2) {
		assertTrue(Utils.doubleEqual(d1, d2));
	}
}