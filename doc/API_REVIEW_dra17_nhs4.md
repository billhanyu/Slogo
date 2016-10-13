#API Review
Ryan Anders
Niklas Sjoquist
dra17, nhs4

##Part 1

What about your API/design is intended to be flexible?

* Reflection could allow for concise code when it comes to reading the users' commands
* Will make it easy to add new commands
* Modularity - each part of the view will be split into its own class

How is your API/design encapsulating your implementation decisions?

* we describe the different classes and their specific methods (w/ return types)
* We need to split it into internal/external API
* A class per GUI element emphasizes the decision to split those elements into their own classes

What exceptions (error cases) might occur in your part and how will you handle them (or not, by throwing)?

* The front end shouldn't encounter many errors
* The back end will notify the front end of any exceptions
* The front end will have to display errors to the user

Why do you think your API/design is good (also define what your measure of good is)?

 * Method/class names are descriptive
 * Methods are used for several things, no overcrowding
 * Modular
 * Need a clear division between private and public methods, as well as internal vs external API

##Part 2

Come up with at least five use cases for your part (it is absolutely fine if they are useful for both teams).

1. User resets the canvas:
 * The GUI has a 'reset' button
 * The click handler for the button calls a function that clears the TurtleLog
 * a TurtleState with the Turtle in the center of the window is added to the TurtleLog
 * Canvas calls render on the TurtleLog, and the view window will be "reset"

2. User enters command into console, presses Run
 * View calls runScript (from Controller)
 * (Controller runs backend)
 * Controller calls View.render
 * Controller calls update methods for various GUI components (command history, variables, user commands)
 
3. User enters a command that doesn't exist, presses Run
  * View calls runScript (from Controller)
  * (Controller runs backend, throws exception)
  * View catches exception
  * View displays a popup window explaining the problem to the user

4. User clicks on a command in the CommandHistoryView
 * Click handler causes the text from that command to be added to whatever is already in the command window (without running)
 * User can make any desired changes/additions
 * User presses Run
 * (see 2 and 3)

5. User adds a user-defined command
* View passes in command window contents
* (Controller runs backend, throws exceptions)
* View catches exceptions, displays error windows
* if no exceptions, Controller calls UserCommandsView.update

How do you think at least one of the "advanced" Java features will help you implement your design?

* Use reflection to assist with reading and instantiating commands without needing a giant switch case
* Bindings will allow the view to update automatically when the view changes
* Enums could be a useful way to keep track of constants (Strings of Toolbar options?)

What feature/design problem are you most excited to work on?

* Using reflection and bindings to create concise, powerful code that makes interaction simple between Model and View.

What feature/design problem are you most worried about working on?

* The same^! I haven't used reflection or bindings before, and I hope I will be able to make them function.