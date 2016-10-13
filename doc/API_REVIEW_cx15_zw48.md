Part 1
=====================

**What about your API/design is intended to be flexible?**
Yes.

**How is your API/design encapsulating your implementation decisions?**
Each class is by itself a module that does one thing only and does it well, and only exposes limited informaiton that is absolutely necessary for communication with another class. The entire View as a frontend representation of model is saparated from model and interfaced with it using a controller. The Controller coordinates the workflow between each Model sub-components such as Interpreter, Executor, InstructionCache, etc. The Model components on the other hand are all unaware of the existence of either the Controller or the View. Modularity as such makes the each module reusable without coupling with many other classes and testable without having to create multiple mocks to isolate external dependents' behavior. 

**What exceptions (error cases) might occur in your part and how will you handle them (or not, by throwing)?**
Exceptions might be generated during compile time or run time execution, such as UnrecognizedIdentifier, MissingParenthesis, OutOfCanvasBoarder, StackOverflow, TimeLimitedExceeded, etc. 

**Why do you think your API/design is good (also define what your measure of good is)?**
Yeah.

Part 2
======================

**Come up with at least five use cases for your part (it is absolutely fine if they are useful for both teams).**
1. Script updated from front end, call controller to update the backend
2. User clears
3. User types in malformed commands and generates exceptions. 
4. Executor executes commands in sequence. 
5. Return a log of turtleStates to controller to render frontend

**How do you think at least one of the "advanced" Java features will help you implement your design?**
Generics and reflextion

**What feature/design problem are you most excited to work on?**
Interpreter

**What feature/design problem are you most worried about working on?**
None
