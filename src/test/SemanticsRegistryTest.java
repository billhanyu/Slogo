package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import exception.SyntacticErrorException;
import model.SemanticsRegistry;
import model.UserCommands;

public class SemanticsRegistryTest {
	
	private SemanticsRegistry semanticsRegistry;
	
	@Before
	public void executedOnceBeforeEach() {
		UserCommands userCommands = new UserCommands();
		semanticsRegistry = new SemanticsRegistry(userCommands); 
	}

	@Test
	public void paramNumProperlyRegistered() {
		try {
			semanticsRegistry.register("to func1 [] []");
			semanticsRegistry.register("to func2 [ :var1 ] []");
			semanticsRegistry.register("to func3 [ :var1 :var2 ] []");
			assertEquals(semanticsRegistry.getNumParam("func1"), 0);
			assertEquals(semanticsRegistry.getNumParam("func2"), 1);
			assertEquals(semanticsRegistry.getNumParam("func3"), 2);
		} catch (SyntacticErrorException e) {
			assertNull(e);
		}
	}
}
