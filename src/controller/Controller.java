package controller;

import java.util.Stack;

import model.Executor;
import model.Interpreter;
import model.StackFrame;

public class Controller {
	
	private Interpreter interpreter;
	private Executor executor;
	private Stack<StackFrame> stack;

	public Controller() {
		interpreter = new Interpreter();
		executor = new Executor();
		stack = new Stack<>();
	}
	
	public void runScript(String script) {
		
	}
	
}
