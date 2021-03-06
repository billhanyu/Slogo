package test;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import exception.SyntacticErrorException;
import exception.UnrecognizedIdentifierException;
import exception.WrongNumberOfArguments;
import javafx.scene.paint.Color;
import model.Interpreter;
import model.LogHolder;
import model.executable.CodeBlock;
import util.Utils;

public class InterpreterTest {
	
	private LogHolder log;
	private Interpreter intr;
	
	@Before
	public void executedOnceBeforeEach() {
		log = new LogHolder();
		intr = new Interpreter();
	}
	
	@Test
	public void nestedCommand() {
		parseAndExecute("fd / sum 10 20 5");
		assertDoubleEqual(log.getTurtleLog(0).peekLast().getPositionY(), -6);
	}
	
	
	@Test
	public void multiParam() {
		parseAndExecute("to poly [ :bogus :num :len ] [ fd :len ] poly 10 10 20");
		assertDoubleEqual(log.getTurtleLog(0).peekLast().getPositionY(), -20);
	}
	
	@Test
	public void nestedParam() {
		parseAndExecute("repeat sum 1 1 [ fd 1 ]");
		assertDoubleEqual(log.getTurtleLog(0).peekLast().getPositionY(), -2);
	}
	
	@Test
	public void nested() {
		parseAndExecute("to nested [ :distance ] [ repeat 4 [ bk :distance ] ] nested 10");
		assertDoubleEqual(log.getTurtleLog(0).peekLast().getPositionY(), 40);
	}

	@Test
	public void recursion() {
		parseAndExecute("to recurse [ :n ] \n\t  [ if :n [ bk :n recurse sum :n -1 ] ] recurse 3");
		assertDoubleEqual(log.getTurtleLog(0).peekLast().getPositionY(), 6);
	}
	
	@Test
	public void ifBranches() {
		parseAndExecute("if 0 [ bk 20 ]");
		assertDoubleEqual(log.getTurtleLog(0).peekLast().getPositionY(), 0);
		parseAndExecute("if 1 [ bk 20 ]");
		assertDoubleEqual(log.getTurtleLog(0).peekLast().getPositionY(), 20);
	}
	
	@Test
	public void ifElse() {
		parseAndExecute("ifelse 1 [ bk 20 ] [ bk 10 ]");
		assertDoubleEqual(log.getTurtleLog(0).peekLast().getPositionY(), 20);
	}
	
	@Test
	public void repeat() {
		parseAndExecute("repeat 2 [ bk :repcount ]");
		assertDoubleEqual(log.getTurtleLog(0).peekLast().getPositionY(), 3);
	}
	
	@Test
	public void dotimes() {
		parseAndExecute("dotimes [ :i 2 ] [ bk :i ]");
		assertDoubleEqual(log.getTurtleLog(0).peekLast().getPositionY(), 3);
	}
	
	@Test
	public void forloop() {
		parseAndExecute("for [ :i 1 3 1 ] [ bk :i ]");
		assertDoubleEqual(log.getTurtleLog(0).peekLast().getPositionY(), 6);
	}
	
	@Test
	public void procedureUsesGlobalVars() {
		parseAndExecute("make :param 20 to func [ ] [ bk :param ] func");
		assertDoubleEqual(log.getTurtleLog(0).peekLast().getPositionY(), 20);
	}
	
	@Test
	public void procedureWithParam() {
		parseAndExecute("to func [ :param ] [ bk :param ] func 10");
		assertDoubleEqual(log.getTurtleLog(0).peekLast().getPositionY(), 10);
	}
	
	@Test
	public void procedureParamWithSameNameButDiffScope() {
		parseAndExecute("make :param 20 to func [ :param ] [ bk :param ] func 10 bk :param");
		assertDoubleEqual(log.getTurtleLog(0).peekLast().getPositionY(), 30);
	}

	@Test
	public void forwardForward() {
		parseAndExecute("fd fd fd 10");
		assertDoubleEqual(log.getTurtleLog(0).peekLast().getPositionY(), -30);
	}
	
	@Test
	public void backBack() {
		parseAndExecute("bk back 10");
		assertDoubleEqual(log.getTurtleLog(0).peekLast().getPositionY(), 20);
	}
	
	@Test
	public void leftLeft() {
		parseAndExecute("lt left 10");
		assertDoubleEqual(log.getTurtleLog(0).peekLast().getHeading(), -20);
	}
	
	@Test
	public void rightRight() {
		parseAndExecute("rt right 10");
		assertDoubleEqual(log.getTurtleLog(0).peekLast().getHeading(), 20);
	}
	
	@Test
	public void setHeading() {
		parseAndExecute("seth 60.5");
		assertDoubleEqual(log.getTurtleLog(0).peekLast().getHeading(), 60.5);
	}

	@Test
	public void Towards() {
		parseAndExecute("towards 1 0");
		assertDoubleEqual(log.getTurtleLog(0).peekLast().getHeading(), 90);
	}
	
	@Test
	public void SetXY() {
		parseAndExecute("setxy 10 5");
		assertDoubleEqual(log.getTurtleLog(0).peekLast().getPositionX(), 10);
		assertDoubleEqual(log.getTurtleLog(0).peekLast().getPositionY(), 5);
		parseAndExecute("setxy 20 8");
		assertDoubleEqual(log.getTurtleLog(0).peekLast().getPositionX(), 20);
		assertDoubleEqual(log.getTurtleLog(0).peekLast().getPositionY(), 8);
	}
	
	@Test
	public void PenDown() {
		parseAndExecute("pd");
		assertTrue(log.getTurtleLog(0).peekLast().getPen().isDown());
	}
	
	@Test
	public void PenUp() {
		parseAndExecute("pu");
		assertTrue(!log.getTurtleLog(0).peekLast().getPen().isDown());
	}
	
	@Test
	public void ShowTurtle() {
		parseAndExecute("st");
		assertTrue(log.getTurtleLog(0).peekLast().isVisible());
	}
	
	@Test
	public void Home() {
		parseAndExecute("setxy 10 5 home");
		assertDoubleEqual(log.getTurtleLog(0).peekLast().getPositionX(), 0);
		assertDoubleEqual(log.getTurtleLog(0).peekLast().getPositionY(), 0);
	}
	
	@Test
	public void ClearScreen() { //same as home, no trail clearing implemented
		parseAndExecute("setxy 10 5 cs");
		assertDoubleEqual(log.getTurtleLog(0).peekLast().getPositionX(), 0);
		assertDoubleEqual(log.getTurtleLog(0).peekLast().getPositionY(), 0);
	}
	
	@Test
	public void HideTurtle() {
		parseAndExecute("ht");
		assertTrue(!log.getTurtleLog(0).peekLast().isVisible());
	}
	
	@Test
	public void makeVar() {
		parseAndExecute("make :dist 10 fd :dist");
		assertDoubleEqual(log.getTurtleLog(0).peekLast().getPositionY(), -10);
	}
	
	@Test
	public void id() {
		double result = parseAndExecute("id");
		assertDoubleEqual(result, 0);
	}
	
	@Test
	public void turtles() {
		double result = parseAndExecute("turtles");
		assertDoubleEqual(result, 1);
	}
	
	@Test
	public void tell() {
		parseAndExecute("tell [ 2 3 5 ]");
		assertDoubleEqual(log.getActiveIDs().size(), 3);
	}
	
	@Test
	public void ask() {
		parseAndExecute("tell [ 2 3 5 ]");
		parseAndExecute("ask [ 1 2 ] [ fd 100 ]");
		assertDoubleEqual(log.getActiveIDs().size(), 3);
	}
	
	@Test
	public void askwith() {
		parseAndExecute("tell [ 2 3 5 ]");
		parseAndExecute("penup");
		parseAndExecute("askwith [ pendown? ] [ fd 100 ]");
		assertDoubleEqual(log.getTurtleLog(0).peekLast().getPositionY(), -100);
	}
	
	@Test
	public void setpensize() {
		parseAndExecute("tell [ 1 2 3 ]");
		parseAndExecute("setpensize 2");
		assertDoubleEqual(log.getTurtleLog(0).peekLast().getPen().getThickness(), 1);
		assertDoubleEqual(log.getTurtleLog(1).peekLast().getPen().getThickness(), 2);
	}
	
	@Test
	public void setpencolor() {
		parseAndExecute("tell [ 1 2 3 ]");
		parseAndExecute("setpencolor 2");
		assertDoubleEqual(log.getPalette().findColorInPalette(log.getTurtleLog(0).peekLast().getPen().getColor()), 1);
		assertDoubleEqual(log.getPalette().findColorInPalette(log.getTurtleLog(1).peekLast().getPen().getColor()), 2);
	}
	
	@Test
	public void setbackground() {
		parseAndExecute("setbackground 2");
		assertDoubleEqual(log.getPalette().findColorInPalette(log.getWorkspaceState().getBackgroundColor()), 2);
	}
	
	@Test
	public void setpalette() {
		parseAndExecute("setpalette 2 128 128 128");
		Color color = Color.color(0.5, 0.5, 0.5);
		assertTrue(log.getPalette().getColor(2).equals(color));
	}
	
	@Test
	public void pencolor() {
		double result = parseAndExecute("pencolor");
		assertDoubleEqual(result, 1);
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
		// TODO cx15 BUG
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
	
	private double parseAndExecute(String script) {
		double result = 0;
		try {
			result = parseAndExecuteHelper(script);
		} catch (SyntacticErrorException | UnrecognizedIdentifierException
				| WrongNumberOfArguments e) {
			assertNull(e);
		}
		return result;
	}
	
	private double parseAndThrow(String script) throws SyntacticErrorException {
		double result = 0;
		try {
			result = parseAndExecuteHelper(script);
		} catch (UnrecognizedIdentifierException | WrongNumberOfArguments e) {
			assertNull(e);
		}
		return result;
	}
	
	private double parseAndExecuteHelper(String script)
			throws SyntacticErrorException, UnrecognizedIdentifierException,
			WrongNumberOfArguments {
		CodeBlock main = intr.parseScript(script);
		return main.execute(log);
	}
	
	private void assertDoubleEqual(double d1, double d2) {
		assertTrue(Utils.doubleEqual(d1, d2));
	}
}
