package controller;
import exception.OutOfBoundsException;
import exception.SyntacticErrorException;
import exception.UnrecognizedIdentifierException;
import exception.WrongNumberOfArguments;
import model.CommandHistory;
import model.Interpreter;
import model.TurtleLog;
import model.executable.CodeBlock;
import view.DisplayLabelReader;
import view.MainView;
import view.TextType;
public class Controller {
	
	private Interpreter interpreter;
	private MainView mainView;
	private TurtleLog log;
	private DisplayLabelReader valueReader;
	private CommandHistory commandHistory;
	private static final String UI_RESOURCES = "resources/labels/EnglishLabels";
	
	public Controller() {
		commandHistory = new CommandHistory();
		interpreter = new Interpreter();
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
		commandHistory.add(main);
		mainView.getEnvironmentView().getCommandHistoryView().update(commandHistory);
		mainView.getEnvironmentView().getGlobalVarsView().update(interpreter.getGlobalVars());
		mainView.getEnvironmentView().getUserCommandsView().update(interpreter.getUserCommands());
	}
	
	public void setLanguage(String language) {
		interpreter.setLanguage(language);
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