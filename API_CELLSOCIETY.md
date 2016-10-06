API_CELLSOCIETY
===============

1. `public void initialize(int[] initialState)` in `CA`

External API for initial configuration. Not sure why it's taking an array of `initialState` since they are representing cells with a 2D array. This API initializes all the cells at the beginning of the simulation.

2. `public void setState(int state)` in `Cell`

External API for setting the cell's state. Keep track of where it is on the grid.

3. `public void writeXML(String fileName)` in `DataOutput`

External API to save the current simulation and output it as an XML file with a file name as `fileName`.

4. `public void setInitialStates(CA ca, String initialStates)` in `DataSetup`

Initialize the states in `CA`, probably calls the method `initialize` in `CA`.

5. `public void run()` in `Graph`

Start the simulation

6. `public void setShapeSettings(Shape shape, Color stroke, Color fill, String className)` in `Graphics`

Configure the settings for shape. Set its shape, stroke, fill and class name.

