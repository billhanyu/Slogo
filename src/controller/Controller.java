package controller;
import java.util.Stack;

import exception.OutOfBoundsException;
import exception.SyntacticErrorException;
import exception.UnrecognizedIdentifierException;
import exception.WrongNumberOfArguments;
import model.Executor;
import model.Interpreter;
import model.GlobalVariables;
import model.TurtleLog;
import model.executable.CodeBlock;
import view.DisplayLabelReader;
import view.MainView;
import view.TextType;
public class Controller {
	
	private Interpreter interpreter;
	private Executor executor;
	private Stack<GlobalVariables> stack;
	private MainView mainView;
	private TurtleLog log;
	private DisplayLabelReader valueReader;
	private static final String UI_RESOURCES = "resources/labels/EnglishLabels";
	
	public Controller() {
		interpreter = new Interpreter();
		executor = new Executor();
		stack = new Stack<>();
		log = new TurtleLog();
		valueReader = new DisplayLabelReader(UI_RESOURCES);
		mainView = new MainView(this);
		log.append(mainView.getCanvas().getCurrentState());
	}
	
	public void runScript(String script) throws UnrecognizedIdentifierException, WrongNumberOfArguments, SyntacticErrorException {
		CodeBlock main = interpreter.parseScript(script);
		double result = main.execute(log);
		try {
			mainView.getCanvas().render(log);
			mainView.getConsole().appendText(""+result, TextType.Plain);
		} catch (OutOfBoundsException e) {
			this.getMainView().getConsole().appendText
				(this.getValueReader().getLabel("OutOfBounds"), TextType.Error);
		}
		
	}
	
	public void setLanguage(String language){
		//TODO: Connect to backend, set language there
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
