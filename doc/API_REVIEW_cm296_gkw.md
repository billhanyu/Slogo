### API Review ###

### SLogo - gkw (team 05), cm296 (team 07) 


#####   Part 1: 

 - What about your API/design is intended to be flexible? 	

	 - We want to make everything as flexible as possible, but specifically what can be displayed. We don't want to hard code that one object be shown, or what is being shown in the "history" tab. We want to keep the display flexible so that the user can change the number, size, etc... of the sprite. This could also involve changing the sprite from a turtle to any other shape. We also need to have the flexibility to easily add buttons to the screen so that it is possible to add new user defined functions that are clickable. 

 - How is your API/design encapsulating your implementation decisions? 
	 - Our API has a lot of classes that each handle small, discrete parts of the project. We will allow limited access to those classes, to control how they interact with each other. What exceptions (error cases) might occur in your part and how will you handle them (or not, by throwing)? We could encounter an error where the user inputs a command that isn't typed correctly. As the user defines new functions, we will add those to the list of known commands and therefore will not throw exceptions when they are typed. We will also create our own exception classes so that we can be as specific as possible in letting the user know what they did wrong. We will make sure that these are displayed to the screen in a helpful, informative way, possibly by highlighting the text where the error is located. This will make the overall user experience much better as they will be able to fix their mistakes with ease. 
	 - 
 - Why do you think your API/design is good (also define what your measure of good is)? 
	 - We think it's good for two main reasons. First, it is very flexible, as we use a lot of classes. Because of this, small changes in one class can have a large impact on how the program operates. Second, it is encapsulated well, in that programs only share what is absolutely necessary for other classes to know.

##### Part 2 
* Come up with at least five use cases for your part (it is absolutely fine if they are useful for both teams). 
 - fd 50 - The front-end will have to pass the command to the back-end to be parsed. Once the back-end has parsed the command and decided what action to take, as well as update any necessary values, and then send that to the front-end which will display the new situation of the turtle moving forward 50 pixels. 
 - Clicking a previously run command - The front-end will register a button-click, send the information to the back end which will then parse the command and decide what action to take. Once it has done that it will transfer back to the front-end to display the new situation. 
 - User inputs a command that doesn't exist - The front-end will pass the command the back-end, which will recognize it as an error. Once this happens, the front-end will show the user the error in their command by highlighting the incorrect section of the command. 
 - User sets a background color for the display window - The front-end will register that a button has been clicked that will update the background color to whichever color the user selected. This case does not require the back-end. 
 - User accesses the help page - The front-end will register a button has been clicked, and the action taken will be to open a new window with the executable commands. How do you think at least one of the "advanced" Java features will help you implement your design? The java feature "enum" will be very helpful with this design, especially with the customizable aspects. We can know the list from which the user will pick the background color, pen color, or turtle image, and they are all interchangeable without affecting the methods used to run the program. 
 
 - What feature/design problem are you most excited to work on? 
	 - I am most excited about working on the canvas, as I believe this is the part of the program that would be most gratifying to the user and really get them interested in programming. 
 - What feature/design problem are you most worried about working on?
	 - I'm most worried about working on making the user-defined commands and commands in history clickable. I'm not really sure how this would work in JavaFx, especially since these are the things that could be changing.
