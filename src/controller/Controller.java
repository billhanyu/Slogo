package controller;

import java.util.Stack;

import model.Executor;
import model.Interpreter;
import model.StackFrame;
import view.MainView;

public class Controller {
	
	private Interpreter interpreter;
	private Executor executor;
	private Stack<StackFrame> stack;
	private MainView mainView;

	public Controller() {
		interpreter = new Interpreter();
		executor = new Executor();
		stack = new Stack<>();
		mainView = new MainView(this);
	}
	
	public void runScript(String script) {
		
	}
	
}
