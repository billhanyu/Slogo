package controller;

import model.Executor;
import model.Interpreter;

public class Controller {
	
	private Interpreter interpreter;
	private Executor executor;

	public Controller() {
		interpreter = new Interpreter();
		executor = new Executor();
	}
	
	public void runScript(String script) {
		
	}
	
}
