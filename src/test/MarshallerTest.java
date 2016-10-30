package test;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import exception.SyntacticErrorException;
import exception.UnrecognizedIdentifierException;
import exception.WrongNumberOfArguments;
import model.Interpreter;
import model.LogHolder;
import model.Marshaller;
import model.executable.CodeBlock;
import util.Utils;

public class MarshallerTest {
	
	private Marshaller marshaller;
	private static final String TEST = "repeat 180 [   fd 5 rt 2 ] ";
	private static final String TEST2 = "dotimes [ :i 2 ] [ bk :i ]";
	
	@Before
	public void executedOnceBeforeEach() {
		marshaller = new Marshaller();
	}
	
	@Test
	public void load() {
		String src = "data/examples/loops/circle.logo";
		try {
			String script = marshaller.load(src);
			assertScriptsEqual(script, TEST);
			// interpreter to take care of the crazy spaces
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void store() {
		try {
			Interpreter intr = new Interpreter();
			CodeBlock main = intr.parseScript(TEST2);
			CodeBlock replica = intr.parseScript(main.toString(false));
			LogHolder log1 = new LogHolder(),
					  log2 = new LogHolder();
			assertTrue(Utils.doubleEqual(main.execute(log1),
					                     replica.execute(log2)));
		} catch (UnrecognizedIdentifierException | WrongNumberOfArguments
				| SyntacticErrorException e) {
			e.printStackTrace();
		}
	}
	
	private void assertScriptsEqual(String s1, String s2) {
		assertTrue(Utils.senitize(s1).equals(Utils.senitize(s2))); 
	}
}
