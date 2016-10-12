# Design for SLogo


### Introduction
TODO

#### Design Overview Diagram

![Design Overview Diagram](design_overview.png "Design Overview Diagram")


### Design Overview

+ Main
    * Would initialize the model and view to start the program.

+ Model
	* Interpreter
    	* Parses the the script text to inistatiate a list of Commands. The script text handles i18n using properties resource file to get translation.
    	* Command is a class with a method member called execute(). A user-defined Command extends Command and essentailly encapsolates a list of other Commands, with its own stack frame and argument lists.
    	* Loops are also implemented this way, except using its parent's stack frame and no argument lists.
    * Turtle Log
    	* The execution of each command potentially changes the state of the turtle, moving, turning, etc. Such updates are recorded in order in an append-only log. When the script is done executing, this log is passed to frontend, which will animates the turtle actions in sequence. 
    * Environment
    	* Record the list of global vars, list of user defined commands, and all command history as the script executes.
    * Update()
    	* The public API of the model for the frontend to pass the user input script or update its states listed above. 
    	* Render Callback
    		* An asynchrous communication paradigm between Model and View. When View updates the model, instead of periodic polling the model to see if execution finishes, the View knows that when scipt execution finishes, model will call render APIs on View to update the view, an interrupt if you will. 

+ View
    * Scipt Editor
    	* Syntax Highlighting, copy and pasting, etc. 
    * Buttons
    	* Run, clear, etc.
    * Canvas
    	* The playground for the turtle that captures all its drawings. 
    * List of global vars, list of user defined commands, and all command history
    	* Frontend representation of the the current states of the program. 
	* Might consist of different subclasses which each hold a different part of the view.
    
+ Exceptions
	* Exceptions generated during compile time or run time execution, such as UnrecognizedIdentifier, MissingParenthesis, OutOfCanvasBoarder, StackOverflow, TimeLimitedExceeded, etc. 

### User Interface


### API Details 


### API Example Code


### Design Considerations 


### Team Responsibilities


