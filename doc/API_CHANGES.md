# API Changes


Back end:

- Removed InstructionCache class
	- function performed by command subclasses
- Removed Executor class
	- functions performed in controller
- Added Executable classes
	- CodeBlock, Command, Variable, Constant
		- Command subclasses
			- ProcedureStub
			- StandardCommand
- Added getName() to all above classes
- Added Translator class
	- added setLanguage
- Interpreter
	- added setLanguage
- TurtleLog
	- added didRender, noRender, peekLast
- TurtleState
	- added setClearScreen(), getPen(), duplicateOnto(Actorstate other)
	- removed isPenDown() (Why?)
- Controller
	- added setLanguage, putScript, getMainView, getValueReader

Front end:

- Canvas
	- added findNextPoints, getTurtleView, inCanvasBounds, setBackgroundColor, setPenColor, getCurrentState, getCanvasWidth, getCanvasHeight
	- added ToroidalCanvas subclass
- EnvironmentView
	- added getGlobalVarsView, getUserCommandsView, getCommandHistoryView
- added EnvironmentListView class
	- getLabelString, update
- CommandHistoryView
	- removed update
	- added getLabelString
- GlobalVarsView
	- removed update
	- added getLabelString
- UserCommandsView
	- removed update
	- added getLabelString
- Console
	- added appendText	
- added DisplayLabelReader class
	- getLabel
- Editor
	- added appendText, runScript, clearText
- added MainView class
	- init, getCnavas, getEditor, getConsole, getEnvironmentView
- added TurtleView
	- setImage, setPositionX, setPositionY, setDirection, setVisible, getImageView
- added View class
	- getUI
- added UserControls class
- added view.floating package
	- FloatingView
		- show, close
	- FloatingViewManager
		- show
	- PenPropertyView
	- TurtleStateView  