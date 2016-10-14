# API Review

## Part 1

1. What is flexible

	It is easy to add new commands to the slogo language because the frontendonly needs a sequence of `TurtleState`s to render the animation of the turtle movements.

2. How is my API encapsulating implementation decisions

	For `Interpreter`, it takes in a String user input and return an `InstructionCache` which will operate on the models. The frontend is hidden from the implementation decisions of parsing the language and executing commands. Similarly, the backend spits out a sequence of `TurtleState`s for the frontend, so the backend is also hidden from how the frontend renders and updates animations.

3. Exceptions

	Parsing error: Interpreter will throw an exception to our controller, which will pass the exception to the frontend text display so that the user knows about the error.

4. Why good

	I think 'good' means the separation between the backend and the frontend. Right now the only two communication methods between the frontend and the backend is: 1. the frontend passes the user text input to the backend. 2. the backend passes the frontend with a `TurtleLog` to render. In this way I think our design is pretty good.

## Part 2

1. Five use cases

	+ User types 'FD 50' and presses 'run'
	+ User clicks on command history to run a command
	+ User changes turtle image
	+ User changes canvas background color
	+ User closes the program

2. Generic types will help us with `TurtleLog`

3. Rendering the animations

4. Highlighting grammar errors in the script editor.
