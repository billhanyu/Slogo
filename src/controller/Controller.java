package controller;
import java.util.Stack;

import exception.SyntacticErrorException;
import exception.UnrecognizedIdentifierException;
import exception.WrongNumberOfArguments;
import model.Executor;
import model.Interpreter;
import model.StackFrame;
import model.TurtleLog;
import model.executable.CodeBlock;
import view.DisplayLabelReader;
import view.MainView;
public class Controller {
	
	private Interpreter interpreter;
	private Executor executor;
	private Stack<StackFrame> stack;
	private MainView mainView;
	private TurtleLog log;
	private final String UILabelFile = "resources/labels/EnglishLabels";
	//TODO: initializing here because initializing in constructor makes object null somehow
	private DisplayLabelReader valueReader = new DisplayLabelReader(UILabelFile);
	
	public Controller() {
		interpreter = new Interpreter();
		executor = new Executor();
		stack = new Stack<>();
		mainView = new MainView(this);
		log = new TurtleLog();
		log.append(mainView.getCanvas().getCurrentState());
		//valueReader = new DisplayValueReader(UILabelFile);
	}
	
	public void runScript(String script) throws UnrecognizedIdentifierException, WrongNumberOfArguments, SyntacticErrorException {
		CodeBlock main = interpreter.parseScript(script);
		main.execute(log);
		mainView.getCanvas().render(log);
	}
	
	public void setLanguage(String language){
		//TO-DO: Connect to backend, set language there
	}
	
	public void putScript(String script) {
		mainView.getEditor().setText(script);
	}
	
	public MainView getMainView() {
		return mainView;
	}
	
	public DisplayLabelReader getValueReader(){
		return valueReader;
	}
	
}
